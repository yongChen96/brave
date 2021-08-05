package com.cloud.brave.log.event;

import com.cloud.brave.api.fegin.SysLoginLogFeignService;
import com.cloud.brave.entity.SysLoginLog;
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
public class BraveLoginLogListener {

    private final SysLoginLogFeignService sysLoginLogFeignService;

    @Async
    @Order
    @EventListener(BraveLoginLogEvent.class)
    public void asyncSaveSysLog(BraveLoginLogEvent event) {
        log.info("[{}], [{}]", event.getSource(), event.getTimestamp());
        SysLoginLog loginLog = (SysLoginLog) event.getSource();
        if (null != loginLog){
            sysLoginLogFeignService.saveLoginLog(loginLog);
        }
    }
}
