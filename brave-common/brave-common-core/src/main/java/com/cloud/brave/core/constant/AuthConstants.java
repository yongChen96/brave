package com.cloud.brave.core.constant;

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
    String AUTHORIZATION_PREFIX = "Bearer ";
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
    String URL_PERM_ROLES_KEY = "system:permission:url_perm_roles";
    String BTN_PERM_ROLES_KEY = "system:permission:btn_perm_roles";
    /**
     * JWT载体key
     */
    String JWT_PAYLOAD_KEY = "payload";
    /**
     * JWT ID 唯一标识
     */
    String JWT_JTI = "jti";
    /**
     * JWT过期时间戳
     */
    String JWT_EXP = "exp";
    /**
     * 黑名单token前缀
     */
    String TOKEN_BLACKLIST_PREFIX = "auth:token:blacklist:";
    /**
     * 用户id
     */
    String USER_ID_KEY = "userId";
    /**
     * 用户名
     */
    String USER_NAME_KEY = "username";
    /**
     * 客户端id
     */
    String CLIENT_ID_KEY = "client_id";
    /**
     * Basic认证前缀
     */
    String BASIC_PREFIX = "Basic ";
    /**
     * grant_type
     */
    String GRANT_TYPE_KEY = "grant_type";
    /**
     * refresh_token
     */
    String REFRESH_TOKEN = "refresh_token";
    /**
     * 加密密码前缀
     **/
    String PASSWORD_PREFIX = "{bcrypt}";
    /**
     * 用户初始密码
     **/
    String USER_INITIAL_PWD = "123456";
}
