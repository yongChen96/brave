package com.cloud.core.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: User
 * @Description: //TODO
 * @Author: yongchen
 * @Date: 2021/5/26 14:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -521946677965800787L;

    /**
     * 用户id
     **/
    private Long id;

    /**
     * 用户呢称
     **/
    private String nickName;

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
