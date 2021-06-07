package com.cloud.auth;

import com.cloud.api.fegin.SysUserFeignService;
import com.cloud.swagger.annotation.EnableBraveSwagger2;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients(clients = {SysUserFeignService.class})
@EnableBraveSwagger2
@EnableDiscoveryClient
@SpringBootApplication
public class BraveAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BraveAuthApplication.class, args);
    }

}
