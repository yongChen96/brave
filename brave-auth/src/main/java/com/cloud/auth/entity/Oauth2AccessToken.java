package com.cloud.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: Oauth2AccessToken
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2021/5/28 15:38
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Oauth2AccessToken implements Serializable {
    private static final long serialVersionUID = 7835846818537351582L;
    /**
     * 访问令牌
     */
    private String token;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 访问令牌头
     */
    private String tokenHead;
    /**
     * 有效时间（秒）
     */
    private int expiresIn;
}
