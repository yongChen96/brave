package com.cloud.brave.mybatisplus.generator.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FileCreateConfig
 * @Description: 文件生成配置
 * @Author: yongchen
 * @Date: 2020/8/31 15:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileCreateConfig {
    private Boolean generateController;
    private Boolean generateService;
    private Boolean generateServiceImpl;
    private Boolean generateEntity;
    private Boolean generateMapper;
    private Boolean generateXml;

    public FileCreateConfig(Boolean falg){
        this.generateController = falg;
        this.generateService = falg;
        this.generateServiceImpl = falg;
        this.generateEntity = falg;
        this.generateMapper = falg;
        this.generateXml = falg;
    }
}
