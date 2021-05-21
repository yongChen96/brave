package com.cloud.swagger.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: GatewaySwaggerAutoConfiguration
 * @Description: 网关swagger配置类
 * @Author: yongchen
 * @Date: 2021/5/18 17:40
 **/
@RequiredArgsConstructor
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ComponentScan("com.cloud.swagger.support")
public class GatewaySwaggerAutoConfiguration {
}
