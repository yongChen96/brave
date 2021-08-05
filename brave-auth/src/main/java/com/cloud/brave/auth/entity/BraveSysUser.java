package com.cloud.brave.auth.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


/**
 * @ClassName: BraveSysUser
 * @Description: 用户信息
 * @Author: yongchen
 * @Date: 2021/5/24 16:56
 **/
@Getter
@Setter
public class BraveSysUser extends User {
    private static final long serialVersionUID = -5867244804850901989L;

    /**
     * 用户id
     **/
    private Long id;
    /**
     * 用户电话
     **/
    private String phone;
    /**
     * 部门id
     **/
    private Long deptId;
    /**
     * 角色id
     **/
    private Long[] roles;

    public BraveSysUser(Long id, String phone, String username, String password, Long deptId, Long[] roles,
                        boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                        boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.phone = phone;
        this.deptId = deptId;
        this.roles = roles;
    }
}
