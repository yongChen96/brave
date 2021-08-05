package com.cloud.brave.core.inject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: BraveUser
 * @Description: 登录用户信息
 * @Author: yongchen
 * @Date: 2021/5/26 14:02
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BraveUser implements Serializable {
    private static final long serialVersionUID = -521946677965800787L;

    /**
     * 用户id
     **/
    private Long id;

    /**
     * 用户姓名
     **/
    private String username;

    /**
     * 用户联系方式
     **/
    private String phone;

    /**
     * 部门id
     **/
    private Long deptId;

    /**
     * 角色信息
     **/
    private Long[] roles;
}
