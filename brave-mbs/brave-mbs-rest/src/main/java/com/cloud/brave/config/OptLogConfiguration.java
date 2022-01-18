package com.cloud.brave.config;

import cn.hutool.core.bean.BeanUtil;
import com.cloud.brave.entity.SysLog;
import com.cloud.brave.log.event.BraveSysLogListener;
import com.cloud.brave.service.SysLogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author yongchen
 * @description: 日志收集配置
 * @date 2022/1/18 10:54
 */
@EnableAsync
@Configuration
public class OptLogConfiguration {

    /**
     * @description: 日志收集
     * @param service
     * @return: com.cloud.brave.log.event.BraveSysLogListener 
     * @author yongchen
     * @date: 2022/1/18 14:22
     */
    @Bean
    public BraveSysLogListener braveSysLogListener(SysLogService service){
        return new BraveSysLogListener((log) -> {
            SysLog sysLog = BeanUtil.copyProperties(log, SysLog.class);
            service.save(sysLog);
        });
    }

}
