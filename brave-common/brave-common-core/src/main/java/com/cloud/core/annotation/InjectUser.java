package com.cloud.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: InjectUser
 * @Description: 用户信息采集
 * @Author: yongchen
 * @Date: 2021/5/26 13:58
 **/
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectUser {
}
