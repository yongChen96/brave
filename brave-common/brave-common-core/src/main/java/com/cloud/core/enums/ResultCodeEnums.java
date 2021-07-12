package com.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ResultCodeEnums
 * @Description: 统一返回响应主体状态码枚举类
 * @Author: yongchen
 * @Date: 2021/5/21 11:16
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultCodeEnums {

    SUCCESS(0,"操作成功"),
    FAILED(-1,"操作失败"),
    UNAUTHORIZED(-2, "资源未授权"),
    TOKENEXPIRED(-3, "token无效或者已过期");

    private Integer code;
    private String msg;
}
