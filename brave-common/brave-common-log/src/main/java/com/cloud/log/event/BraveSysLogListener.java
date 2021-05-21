package com.cloud.log.event;

import com.cloud.log.entity.SysLog;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

/**
 * @ClassName: BraveSysLogListener
 * @Description: 系统日志监听器
 * @Author: yongchen
 * @Date: 2021/5/21 16:10
 **/
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class BraveSysLogListener {

    private Consumer<SysLog> consumer;


    @Async
    @Order
    @EventListener(BraveSysLogEvent.class)
    public void asyncSaveSysLog(BraveSysLogEvent event) {
        log.info("[{}], [{}]", event.getSource(), event.getTimestamp());
        SysLog sysLog = (SysLog) event.getSource();
        if (null != sysLog){
            this.consumer.accept(sysLog);
        }

    }
}
