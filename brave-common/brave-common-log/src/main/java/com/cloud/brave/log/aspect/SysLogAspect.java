package com.cloud.brave.log.aspect;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.cloud.brave.core.constant.CommonConstants;
import com.cloud.brave.core.utils.JwtUtils;
import com.cloud.brave.entity.SysLog;
import com.cloud.brave.core.result.Result;
import com.cloud.brave.log.event.BraveSysLogEvent;
import com.cloud.brave.log.utils.IPUtils;
import com.cloud.brave.log.annotation.BraveSysLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: SysLogAspect
 * @Description: 日志统一处理切面类
 * @Author: yongchen
 * @Date: 2021/5/21 11:43
 **/
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    @Resource
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.cloud.brave.log.annotation.BraveSysLog)")
    private void sysLogAspect() {
    }

    /**
     * @Author: yongchen
     * @Description: 返回通知
     * @Date: 16:32 2021/5/21
     * @Param: [joinPoint]
     * @return: java.lang.Object
     **/
    @AfterReturning(pointcut = "sysLogAspect()", returning = "res")
    public Object doAfterReturning(JoinPoint joinPoint, Object res) throws Throwable {
        //当前信息
        long startTime = System.currentTimeMillis();
        long threadId = Thread.currentThread().getId();
        log.info("当前时间戳={}", startTime);
        log.info("当前线程id={}", threadId);
        SysLog sysLog = buildSysLog(joinPoint, res);
        applicationContext.publishEvent(new BraveSysLogEvent(sysLog));
        return sysLog;
    }

    /**
     * @Author: yongchen
     * @Description: 异常日志保存
     * @Date: 16:36 2021/5/21
     * @Param:
     * @return: java.lang.Object
     **/
    @AfterThrowing(pointcut = "sysLogAspect()", throwing = "e")
    public Object doAfterThrowing(JoinPoint joinPoint, Exception e) throws Throwable {
        SysLog sysLog = buildSysLog(joinPoint, null);
        sysLog.setErrorDesc(e.getMessage());
        sysLog.setErrorDetail(e.getMessage());
        applicationContext.publishEvent(new BraveSysLogEvent(sysLog));
        return sysLog;
    }

    /**
     * @Author: yongchen
     * @Description: 组装日志实体
     * @Date: 17:22 2021/5/21
     * @Param: [joinPoint]
     * @return: com.cloud.log.entity.SysLog
     **/
    private SysLog buildSysLog(JoinPoint joinPoint, Object res) throws Throwable {
        //记录请求信息
        SysLog sysLog = new SysLog();
        //获取当前请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取自定义注解value值
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        BraveSysLog braveSysLog = method.getAnnotation(BraveSysLog.class);
        String value = braveSysLog.value();
        sysLog.setDescription(value);
        //获取请求信息
        sysLog.setRequestUri(request.getRemoteAddr());
        sysLog.setHttpMethod(request.getMethod());
        String uri = request.getRequestURI();
        String requestUri = StrUtil.removeSuffix(uri, URLUtil.url(uri).getPath());
        sysLog.setRequestUri(requestUri);
        //访问IP地址
        String ipAddr = IPUtils.getIpAddr(request);
        sysLog.setRequestIp(ipAddr);
        sysLog.setRequestCity(IPUtils.getCityInfo(ipAddr));
        //获取访问浏览器信息
        UserAgent userAgent = UserAgentUtil.parse(request.getHeader(CommonConstants.USER_AGENT));
        if (userAgent.isMobile()) {
            sysLog.setDeviceType(userAgent.getPlatform().getName());
        } else {
            sysLog.setDeviceType(CommonConstants.COMPUTER);
        }
        sysLog.setOperateSystem(userAgent.getOs().getName());
        sysLog.setBrowser(String.format("%s %s", userAgent.getBrowser().toString(), userAgent.getVersion()));
        sysLog.setParams(getParameter(method, joinPoint.getArgs()));
        if (res != null) {
            Result convert = Convert.convert(Result.class, res);
            if (convert.getCode() == 0) {
                sysLog.setType("0");
            } else {
                sysLog.setType("1");
            }
            String s = StringUtils.substring(convert.toString(), 0, 65535);
            Long userId = JwtUtils.getUserId();
            sysLog.setCreateUser(userId);
            sysLog.setUpdateUser(userId);
            sysLog.setCreateName(JwtUtils.getUsername());
            sysLog.setResult(s);
        }
        return sysLog;
    }

    /**
     * @Author: yongchen
     * @Description: 参数解析
     * @Date: 16:34 2021/5/21
     * @Param: [method, args]
     * @return: java.lang.String
     **/
    private String getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //排除附件参数
            Object arg = args[i];
            if (arg instanceof InputStreamSource) {
                continue;
            }
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (null != requestBody) {
                argList.add(arg);
            }
            //将RequestParam修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (null != requestParam) {
                HashMap<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (StringUtils.isNotBlank(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, arg);
                argList.add(parameters);
            }
        }
        return JSON.toJSONString(argList);
    }


}
