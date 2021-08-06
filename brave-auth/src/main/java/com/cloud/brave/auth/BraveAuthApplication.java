package com.cloud.brave.auth;

import com.cloud.brave.api.fegin.SysLoginLogFeignService;
import com.cloud.brave.api.fegin.SysUserFeignService;
import com.cloud.brave.swagger.annotation.EnableBraveSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableBraveSwagger2
@EnableDiscoveryClient
@EnableFeignClients(clients = {SysUserFeignService.class, SysLoginLogFeignService.class})
@SpringBootApplication
public class BraveAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BraveAuthApplication.class, args);
    }

}
