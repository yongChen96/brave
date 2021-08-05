package com.cloud.brave.log.event;

import com.cloud.brave.entity.SysLog;
import com.cloud.brave.entity.SysLoginLog;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName: BraveSysLogEvent
 * @Description: 系统日志事件
 * @Author: yongchen
 * @Date: 2021/5/21 16:06
 **/
public class BraveLoginLogEvent extends ApplicationEvent {
    private static final long serialVersionUID = -5913118165674283113L;

    public BraveLoginLogEvent(SysLoginLog source){
        super(source);
    }
}
