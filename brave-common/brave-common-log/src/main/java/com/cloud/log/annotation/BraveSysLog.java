package com.cloud.log.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: BraveSysLog
 * @Description: 日志统一处理切面类
 * @Author: yongchen
 * @Date: 2021/5/21 11:40
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BraveSysLog {

    /**
     * 描述信息
     * @return java.lang.String
     */
    String value();
}
