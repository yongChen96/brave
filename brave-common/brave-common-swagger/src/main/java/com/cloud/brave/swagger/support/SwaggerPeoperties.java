package com.cloud.brave.swagger.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: SwaggerPeoperties
 * @Description: Swagger配置类
 * @Author: yongchen
 * @Date: 2021/5/28 17:38
 **/
@Data
@ConfigurationProperties("swagger")
public class SwaggerPeoperties {
    /**
     * 是否开启swagger
     */
    private Boolean enabled = true;
    /**
     * swagger扫描包路径
     **/
    private String basePackage;

    /**
     * 标题
     **/
    private String title;
    /**
     * 描述
     **/
    private String description;

    /**
     * 版本
     **/
    private String version;

    /**
     * 许可证
     **/
    private String license;

    /**
     * 许可证URL
     **/
    private String licenseUrl;

    /**
     * 服务条款URL
     **/
    private String termsOfServiceUrl;
    /**
     * 联系人信息
     */
    private Contact contact;

}
