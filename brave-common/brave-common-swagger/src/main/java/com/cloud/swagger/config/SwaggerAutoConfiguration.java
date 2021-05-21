package com.cloud.swagger.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

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
}
