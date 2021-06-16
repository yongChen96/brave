package com.cloud.gateway.component;

/**
 * @ClassName: AuthConstants
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2021/6/15 16:38
 **/
public interface AuthConstants {

    /**
     * JWT令牌前缀
     */
    String AUTHORIZATION_PREFIX = "bearer ";
    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "ROLE_";
    /**
     * JWT存储权限属性
     */
    String JWT_AUTHORITIES_KEY = "authorities";
    /**
     * 超级管理源
     */
    String ROOT = "ROOT";
}
