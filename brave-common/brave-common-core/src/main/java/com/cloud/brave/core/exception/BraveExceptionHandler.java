package com.cloud.brave.core.exception;

import com.cloud.brave.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: BraveExceptionHandler
 * @Description: 统一异常处理器
 * @Author: yongchen
 * @Date: 2021/5/25 14:08
 **/
@Slf4j
@Component
@RestControllerAdvice
public class BraveExceptionHandler {
    
    /**
     * @Author: yongchen
     * @Description: 全局统一异常拦截
     * @Date: 16:30 2021/5/25
     * @Param: [e]
     * @return: com.cloud.core.result.Result<?>
     **/
    @ExceptionHandler(Throwable.class)
    public Result<?> handlerException(Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        if (response.getContentType() != null) {
            response.setContentType("application/json");
        }
        if (response.getHeader("Expires") != null) {
            response.setDateHeader("Expires", 1);
        }
        return doHandlerException(e, request.getRequestURI());
    }
    
    /**
     * @Author: yongchen
     * @Description: 日志处理
     * @Date: 16:30 2021/5/25
     * @Param: [throwable, uri]
     * @return: com.cloud.core.result.Result<java.lang.Object>
     **/
    private Result<Object> doHandlerException(Throwable throwable, String uri) {
        //打印错误日志
        printErrorLog(throwable, uri);

        List<Throwable> throwableList = ExceptionUtils.getThrowableList(throwable);
        for (Throwable thr : throwableList) {
            Result<Object> result = handler(thr);
            if (null != null){
                return result;
            }
        }
        return Result.failed(BraveExceptionCodeEnum.SYSTEM_ERROR.getCode(), BraveExceptionCodeEnum.SYSTEM_ERROR.getMessage());
    }

    /**
     * @Author: yongchen
     * @Description: 打印完整错误日志
     * @Date: 14:29 2021/5/25
     * @Param: [throwable, uri]
     * @return: void
     **/
    private void printErrorLog(Throwable throwable, String uri) {
        ByteArrayOutputStream stream = null;
        PrintStream printStream = null;
        try {
            stream = new ByteArrayOutputStream();
            printStream = new PrintStream(stream);
            throwable.printStackTrace(printStream);
            stream.write("---------------------------------------------------------------".getBytes());
            log.error("在全局异常拦截器中发现异常： uri: {} \r\n{},", uri, stream.toString("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("在全局异常拦截器中发现异常：{}", stream.toString());
        } finally {
            printStream.close();
        }
    }

    /**
     * @Author: yongchen
     * @Description: 返回错误日志信息
     * @Date: 14:49 2021/5/25
     * @Param: [throwable]
     * @return: void
     **/
    private Result<Object> handler(Throwable throwable) {
        // 系统自定义异常类
        if (throwable instanceof BraveException) {
            BraveException braveException = (BraveException) throwable;
            return Result.failed(braveException.getCode(), braveException.getMessage());
        } else if (throwable instanceof HttpMessageNotReadableException) {
            // json反序列化失败
            String message = throwable.getMessage();
            if (StringUtils.isNotBlank(message)) {
                if (message.contains("Could not read document:")) {
                    String msg = "无法正确的解析json类型的参数：" + message.substring(
                            message.indexOf("Could not read document:") + "Could not read document:".length(),
                            message.indexOf(" at "));
                    return Result.failed(BraveExceptionCodeEnum.PARAMETER_PARSING_FAIL.getCode(), msg);
                }
            }
        } else if (throwable instanceof BindException) {
            // 对象参数类型解析异常
            BindException bindException = (BindException) throwable;
            StringBuilder message = new StringBuilder();
            List<FieldError> fieldErrors = bindException.getFieldErrors();
            fieldErrors.forEach(filed ->
                    message.append("参数对象【")
                            .append(filed.getObjectName())
                            .append("】的字段【")
                            .append(filed.getField())
                            .append("】的值【")
                            .append(filed.getRejectedValue())
                            .append("】与实际类型不匹配"));
            return Result.failed(BraveExceptionCodeEnum.PARAMETER_TYPE_FAIL.getCode(), message.toString());
        } else if (throwable instanceof MethodArgumentTypeMismatchException) {
            // 方法参数错误异常
            MethodArgumentTypeMismatchException methodArgumentTypeMismatchException = (MethodArgumentTypeMismatchException) throwable;
            StringBuilder message = new StringBuilder();
            message.append("参数【")
                    .append(methodArgumentTypeMismatchException.getName())
                    .append("】的值【")
                    .append(methodArgumentTypeMismatchException.getValue())
                    .append("】与实际类型【")
                    .append(methodArgumentTypeMismatchException.getRequiredType())
                    .append("】不匹配");
            return Result.failed(BraveExceptionCodeEnum.METHOD_PARRMETER_FAIL.getCode(), message.toString());
        } else if (throwable instanceof IllegalStateException) {
            // 无效参数异常
            return Result.failed(BraveExceptionCodeEnum.INVALID_PARAMETER_FAIL.getCode(), BraveExceptionCodeEnum.INVALID_PARAMETER_FAIL.getMessage());
        } else if (throwable instanceof MissingServletRequestParameterException) {
            // 缺少必填参数异常
            MissingServletRequestParameterException missingServletRequestParameterException = (MissingServletRequestParameterException) throwable;
            StringBuilder message = new StringBuilder();
            message.append("缺少必填的【")
                    .append(missingServletRequestParameterException.getParameterType())
                    .append("】类型的参数【")
                    .append(missingServletRequestParameterException.getParameterName())
                    .append("】");
            return Result.failed(BraveExceptionCodeEnum.MISS_MUST_PARAMETER.getCode(), message.toString());
        } else if (throwable instanceof NullPointerException) {
            // 空指针异常
            return Result.failed(BraveExceptionCodeEnum.NULL_POINT__FAIL.getCode(), BraveExceptionCodeEnum.NULL_POINT__FAIL.getMessage());
        } else if (throwable instanceof SQLException) {
            // 数据库异常
            return Result.failed(BraveExceptionCodeEnum.SQL_ERROR.getCode(), BraveExceptionCodeEnum.SQL_ERROR.getMessage());
        } else if (throwable instanceof DataIntegrityViolationException) {
            // 数据库异常(更新（update或insert）数据库时，新的数据违反了完整性，例如主键重复)
            return Result.failed(BraveExceptionCodeEnum.SQL_ERROR.getCode(), BraveExceptionCodeEnum.SQL_ERROR.getMessage());
       /* } else if (throwable instanceof PersistenceException) {
            // 数据库异常(字段类型转换错误)
            return Result.failed(BraveExceptionCodeEnum.SQL_ERROR.getCode(), BraveExceptionCodeEnum.SQL_ERROR.getMessage());*/
        } else if (throwable instanceof HttpMediaTypeNotSupportedException) {
            // Http请求Content-Type类型异常
            HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException = (HttpMediaTypeNotSupportedException) throwable;
            MediaType contentType = httpMediaTypeNotSupportedException.getContentType();
            if (null != contentType) {
                StringBuilder message = new StringBuilder();
                message.append("请求类型(Content-Type)【")
                        .append(contentType.toString())
                        .append("】与实际接口请求类型不匹配");
                return Result.failed(BraveExceptionCodeEnum.REQUEST_TYPE_FAIL.getCode(), message.toString());
            }
            return Result.failed(BraveExceptionCodeEnum.REQUEST_TYPE_FAIL.getCode(), BraveExceptionCodeEnum.REQUEST_TYPE_FAIL.getMessage());
        } else if (throwable instanceof MultipartException) {
            // 附加上传异常
            return Result.failed(BraveExceptionCodeEnum.FILE_UPDATE_FIAL.getCode(), BraveExceptionCodeEnum.FILE_UPDATE_FIAL.getMessage());
        } else if (throwable instanceof ConstraintViolationException) {
            // 信息校验异常
            ConstraintViolationException e = (ConstraintViolationException) throwable;
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
            return Result.failed(BraveExceptionCodeEnum.BASE_VALID_PARAM.getCode(), BraveExceptionCodeEnum.BASE_VALID_PARAM.getMessage());
        } else if (throwable instanceof MethodArgumentNotValidException) {
            // 信息校验异常
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) throwable;
            return Result.failed(BraveExceptionCodeEnum.BASE_VALID_PARAM.getCode(), methodArgumentNotValidException.getBindingResult().getFieldError().getDefaultMessage());
        }
        return null;
    }
}
