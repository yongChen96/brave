package com.cloud.swagger.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: Contact
 * @Description: 接口联系人信息
 * @Author: yongchen
 * @Date: 2021/5/28 17:45
 **/
@Data
@Configuration
@ConfigurationProperties("contact")
public class Contact {
    /**
     * 联系人
     **/
    private String name;

    /**
     * 联系人url
     **/
    private String url;

    /**
     * 联系人email
     **/
    private String email;
}
