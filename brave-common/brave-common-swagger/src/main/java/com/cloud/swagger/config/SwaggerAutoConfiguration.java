package com.cloud.swagger.config;

import com.cloud.swagger.support.SwaggerPeoperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;

/**
 * @ClassName: SwaggerAutoConfiguration
 * @Description: swagger配置 禁用方法1：使用注解@Profile({"dev","test"})表示在开发或测试环境开启，而在生产关闭。
 *                           (推荐使用） 禁用方法2：使用注解@ConditionalOnProperty(name = "swagger.enable", havingValue = "true") 然后在测试配置或者开发配置中添加swagger.enable=true即可开启，生产环境不填则默认关闭Swagger.
 * @Author: yongchen
 * @Date: 2021/5/18 17:43
 **/
@Configuration
@EnableAutoConfiguration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "swagger.enabled", matchIfMissing = true)
public class SwaggerAutoConfiguration {

    @Resource
    private SwaggerPeoperties swaggerPeoperties;

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPeoperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(swaggerPeoperties.getTitle())
                .description(swaggerPeoperties.getDescription())
                .license(swaggerPeoperties.getLicense())
                .licenseUrl(swaggerPeoperties.getLicenseUrl())
                .termsOfServiceUrl(swaggerPeoperties.getTermsOfServiceUrl())
                .contact(new Contact(swaggerPeoperties.getContact().getName(), swaggerPeoperties.getContact().getUrl(), swaggerPeoperties.getContact().getEmail()))
                .version(swaggerPeoperties.getVersion()).build();
    }

}
