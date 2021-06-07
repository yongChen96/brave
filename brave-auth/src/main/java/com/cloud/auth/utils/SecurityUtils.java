package com.cloud.auth.utils;

import com.cloud.auth.entity.BraveSysUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName: SecurityUtils
 * @Description: Security工具类
 * @Author: yongchen
 * @Date: 2021/6/7 13:44
 **/
@UtilityClass
public class SecurityUtils {

    /**
     * @Author: yongchen
     * @Description: 获取用户信息
     * @Date: 13:50 2021/6/7
     * @Param: []
     * @return: com.cloud.auth.entity.BraveSysUser
     **/
    public BraveSysUser getUser() {
        Authentication authentication = getAuthentication();
        return getBraveUser(authentication);
    }

    /**
     * 获取Authentication
     **/
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取登录用户信息
     **/
    public BraveSysUser getBraveUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof BraveSysUser) {
            return (BraveSysUser) principal;
        }
        return null;
    }
}
