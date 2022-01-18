package com.cloud.brave.log.event;

import com.cloud.brave.entity.SysLog;
import com.cloud.brave.log.annotation.BraveSysLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @ClassName: BraveSysLogListener
 * @Description: 异步监听日志事件
 * @Author: yongchen
 * @Date: 2021/5/21 16:10
 **/
@Slf4j
@Component
public class BraveSysLogListener {

    private Consumer<SysLog> consumer;

    public BraveSysLogListener(Consumer<SysLog> consumer){
        this.consumer = consumer;
    }

    /**
     * @description: 异步日志保存
     * @param event
     * @return: void
     * @author yongchen
     * @date: 2022/1/18 11:01
     */
    @Async
    @Order
    @EventListener(BraveSysLogEvent.class)
    public void asyncSaveSysLog(BraveSysLogEvent event) {
        log.info("[{}], [{}]", event.getSource(), event.getTimestamp());
        SysLog sysLog = (SysLog) event.getSource();
        if (null != sysLog){
            consumer.accept(sysLog);
        }
    }
}
