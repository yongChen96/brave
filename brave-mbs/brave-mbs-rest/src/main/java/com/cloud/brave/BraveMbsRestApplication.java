package com.cloud.brave;

import com.cloud.swagger.annotation.EnableBraveSwagger2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableBraveSwagger2
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.cloud.brave.mapper"})
@SpringBootApplication
public class BraveMbsRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BraveMbsRestApplication.class, args);
    }

}
