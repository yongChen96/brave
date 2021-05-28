package com.cloud.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BraveExceptionCodeEnum
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2021/5/25 14:57
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BraveExceptionCodeEnum {

    SYSTEM_ERROR(-1, "系统错误，请联系管理员"),
    PARAMETER_PARSING_FAIL(-2, "参数解析失败"),
    PARAMETER_TYPE_FAIL(-3, "参数类型错误"),
    METHOD_PARRMETER_FAIL(-4, "方法参数类型不匹配"),
    INVALID_PARAMETER_FAIL(-5, "无效参数"),
    MISS_MUST_PARAMETER(-5, "必填参数不能为空"),
    NULL_POINT__FAIL(-6, "空指针异常"),
    SQL_ERROR(-7, "数据库异常"),
    REQUEST_TYPE_FAIL(-8, "请求类型不匹配"),
    FILE_UPDATE_FIAL(-9,"附件上传异常"),
    BASE_VALID_PARAM(-10, "统一验证参数异常");

    private Integer code;
    private String message;
}
