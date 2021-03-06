package com.cloud.brave.core.constant;

/**
 * @ClassName: CacheConstants
 * @Description: 缓存key常量类
 * @Author: yongchen
 * @Date: 2021/5/21 10:08
 **/
public interface CacheConstants {

    /**
     * 用户信息缓存
     **/
    String USER_DETAILS_CACHE = "user_details";
    /**
     * 验证码缓存key
     **/
    String CAPTCHA_KEY = "captcha_";
    /**
     * 验证码有效期,默认 60秒
     */
    long CODE_TIME = 60;
}
