package com.cloud.auth.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


/**
 * @ClassName: BraveSysUser
 * @Description: 用户信息
 * @Author: yongchen
 * @Date: 2021/5/24 16:56
 **/
public class BraveSysUser extends User {
    private static final long serialVersionUID = -5867244804850901989L;

    /**
     * 用户id
     **/
    @Getter
    private Long id;

    @Getter
    private String nickName;

    public BraveSysUser(Long id, String nickName, String username, String password,
                        boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.nickName = nickName;
    }
}
