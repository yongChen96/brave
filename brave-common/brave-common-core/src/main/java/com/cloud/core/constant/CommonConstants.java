package com.cloud.core.constant;

/**
 * @ClassName: CommonConstants
 * @Description: 公共常量类
 * @Author: yongchen
 * @Date: 2021/5/21 10:09
 **/
public interface CommonConstants {

    /**
     * token 头部
     **/
    String token_head = "Bearer ";

    /**
     * 账号是否被锁 0：正常 1：锁定
     **/
    String IS_LOCK_NO = "0";
    String IS_LOCK_YES = "1";

    /**
     * 逻辑删除标识 0: 正常 1：已删除
     **/
    String NOT_DELETED = "0";
    String DELETED = "1";

    /**
     * 逻辑删除标识 0: 正常 1：已删除
     **/
    String CAPTCH_PNG = "1";
    String CAPTCH_GIF = "2";
    String CAPTCH_CHINESE = "3";
    String CAPTCH_ARITHMETIC = "4";
}
