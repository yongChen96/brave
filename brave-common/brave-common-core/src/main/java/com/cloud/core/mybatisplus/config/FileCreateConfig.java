package com.cloud.core.mybatisplus.config;

import lombok.Data;

/**
 * @ClassName: FileCreateConfig
 * @Description: 文件生成配置
 * @Author: yongchen
 * @Date: 2020/8/31 15:21
 **/
@Data
public class FileCreateConfig {
    private Boolean generateController;
    private Boolean generateService;
    private Boolean generateServiceImpl;
    private Boolean generateEntity;
    private Boolean generateMapper;
    private Boolean generateXml;

    public FileCreateConfig(){
        this.generateController = true;
        this.generateService = true;
        this.generateServiceImpl = true;
        this.generateEntity = true;
        this.generateMapper = true;
        this.generateXml = true;
    }
}
