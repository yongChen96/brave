package com.cloud.brave;

import com.cloud.api.fegin.SysLogFeignService;
import com.cloud.api.fegin.SysUserFeignService;
import com.cloud.swagger.annotation.EnableBraveSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@EnableBraveSwagger2
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.cloud"})
@MapperScan(basePackages = {"com.cloud.brave.mapper"})
@EnableFeignClients(clients = {SysLogFeignService.class})
@SpringBootApplication
public class BraveMbsRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BraveMbsRestApplication.class, args);
    }

}
