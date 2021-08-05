package com.cloud.brave.auth.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName: BraveLoginInfo
 * @Description: 用户登录信息
 * @Author: yongchen
 * @Date: 2021/6/18 16:20
 **/
@Data
public class BraveLoginInfo implements Serializable {
    private static final long serialVersionUID = 414576996197148519L;

    /**
     * 客户端id
     **/
    @NotNull(message = "客户端id不能为空")
    @ApiModelProperty(value = "客户端id")
    private String clientId;

    /**
     * 客户端Secret
     **/
    @NotNull(message = "客户端Secret不能为空")
    @ApiModelProperty(value = "客户端Secret")
    private String clientSecret;

    /**
     * 授权类型
     **/
    @NotNull(message = "授权类型不能为空")
    @ApiModelProperty(value = "授权类型")
    private String grantType;

    /**
     * 用户密码
     **/
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 用户名
     **/
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 验证码code
     **/
    @NotNull(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码code")
    private String code;
}
