package com.cloud.auth.entity;

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
    private String userName;

    /**
     * 用户联系方式
     **/
    private String phone;

    /**
     * 用户性别
     **/
    private String sex;

    /**
     * 用户生日
     **/
    private String birthday;
}
