package com.cloud.log.event;

import com.cloud.api.fegin.SysLogFeignService;
import com.cloud.brave.entity.SysLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * @ClassName: BraveSysLogListener
 * @Description: 系统日志监听器
 * @Author: yongchen
 * @Date: 2021/5/21 16:10
 **/
@Slf4j
@RequiredArgsConstructor
public class BraveSysLogListener {

    private final SysLogFeignService sysLogFeignService;

    @Async
    @Order
    @EventListener(BraveSysLogEvent.class)
    public void asyncSaveSysLog(BraveSysLogEvent event) {
        log.info("[{}], [{}]", event.getSource(), event.getTimestamp());
        SysLog sysLog = (SysLog) event.getSource();
        if (null != sysLog){
            sysLogFeignService.saveSysLog(sysLog);
        }
    }
}
