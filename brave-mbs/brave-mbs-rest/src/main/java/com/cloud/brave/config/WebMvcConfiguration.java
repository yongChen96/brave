package com.cloud.brave.config;

import com.cloud.auth.inject.resolver.InjectUserResolver;
import com.cloud.core.config.BaseConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebMvcConfiguration
 * @Description: Web配置文件
 * @Author: yongchen
 * @Date: 2021/6/7 15:31
 **/
@Configuration
public class WebMvcConfiguration extends BaseConfig implements WebMvcConfigurer {

    @Bean
    public InjectUserResolver injectUserResolver() {
        return new InjectUserResolver();
    }
}
