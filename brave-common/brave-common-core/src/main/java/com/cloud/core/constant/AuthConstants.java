package com.cloud.core.constant;

/**
 * @ClassName: AuthConstants
 * @Description: Auth常量类
 * @Author: yongchen
 * @Date: 2021/6/15 16:38
 **/
public interface AuthConstants {
    /**
     * 认证请求头key
     */
    String AUTHORIZATION_KEY = "Authorization";
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
    /**
     * app
     */
    String APP_API_PATTERN="/*/app-api/**";
    /**
     * 权限缓存key
     **/
    String URL_PERM_ROLES_KEY = "system:permission:url_perm_roles:";
    String BTN_PERM_ROLES_KEY = "system:permission:btn_perm_roles:";
    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";
    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";
    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";
}
