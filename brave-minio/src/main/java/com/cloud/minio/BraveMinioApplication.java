package com.cloud.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BraveMinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(BraveMinioApplication.class, args);
    }

}
