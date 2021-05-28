package com.cloud.core.config;

import com.cloud.core.IdGenerate.SnowflakeIDGenerate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName: BaseConfig
 * @Description: 基础配置信息
 * @Author: yongchen
 * @Date: 2021/5/25 11:16
 **/
public abstract class BaseConfig {

    @Bean
    public SnowflakeIDGenerate setMachineCode(@Value("${brave.machine-code}") Long machineCode){
        return new SnowflakeIDGenerate(machineCode);
    }
}
