package com.cloud.brave.config;

import com.cloud.brave.core.base.config.BaseConfig;
import com.cloud.brave.core.inject.resolver.InjectUserResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ClassName: WebMvcConfiguration
 * @Description: Web配置文件
 * @Author: yongchen
 * @Date: 2021/6/7 15:31
 **/
@Configuration
public class WebMvcConfiguration extends BaseConfig implements WebMvcConfigurer {

    /**
     * @Author yongchen
     * @Description 返回序列化
     * @Date 17:22 2021/7/6
     * @param builder
     * @return com.fasterxml.jackson.databind.ObjectMapper
     **/
    @Override
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return super.jacksonObjectMapper(builder);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(getInjectUserResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    @Bean
    public InjectUserResolver getInjectUserResolver(){
        return new InjectUserResolver();
    }
}
