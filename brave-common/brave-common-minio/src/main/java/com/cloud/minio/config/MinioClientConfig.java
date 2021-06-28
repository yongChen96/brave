package com.cloud.minio.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveBucketArgs;
import io.minio.messages.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description: MinioClient
 * @date 2021/6/28 11:40
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MinioClientConfig {

    private final MinioPropreties minioPropreties;

    @Bean
    public MinioClient createMinioClient(){
        return MinioClient.builder()
                .endpoint(minioPropreties.getEndpoint())
                .credentials(minioPropreties.getAccessKey(), minioPropreties.getSecretKey())
                .build();
    }
}
