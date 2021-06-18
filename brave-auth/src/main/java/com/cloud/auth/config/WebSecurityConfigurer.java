package com.cloud.auth.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName: WebSecurityConfigurer
 * @Description: WebSecurityConfigurer
 * @Author: yongchen
 * @Date: 2021/5/26 17:10
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/rsa/getPublicKey", "/oauth/*", "/sysUser/getUserInfo")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/webjars/**", "/doc.html", "/swagger-resources/**", "/v2/api-docs")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
