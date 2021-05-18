package com.cloud.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BraveAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(BraveAuthApplication.class, args);
    }

}
