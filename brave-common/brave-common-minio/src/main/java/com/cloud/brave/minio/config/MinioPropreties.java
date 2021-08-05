package com.cloud.brave.minio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @version 1.0
 * @description: MinioProperties
 * @date 2021/6/28 11:31
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioPropreties {
    /**
     * minio endpoint
     **/
    private String endpoint;
    /**
     * minio accessKey
     **/
    private String accessKey;
    /**
     * minio secretKey
     **/
    private String secretKey;
    /**
     * 桶名称
     **/
    private String bucketName;
}
