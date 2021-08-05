package com.cloud.brave;

import com.cloud.brave.api.fegin.SysLogFeignService;
import com.cloud.brave.swagger.annotation.EnableBraveSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableBraveSwagger2
@EnableDiscoveryClient
@EnableFeignClients(clients = {SysLogFeignService.class})
@SpringBootApplication
public class BraveMbsRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BraveMbsRestApplication.class, args);
    }

}
