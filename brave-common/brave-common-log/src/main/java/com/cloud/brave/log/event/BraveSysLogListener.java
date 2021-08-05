package com.cloud.brave.log.event;

import com.cloud.brave.api.fegin.SysLogFeignService;
import com.cloud.brave.entity.SysLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName: BraveSysLogListener
 * @Description: 异步监听日志事件
 * @Author: yongchen
 * @Date: 2021/5/21 16:10
 **/
@Slf4j
@Component
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
