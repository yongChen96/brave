package com.cloud.brave.mq.service;

/**
 * @author yongchen
 * @description: 消息发布服务
 * @date 2021/8/20 17:29
 */
public interface MqSendMsgService {

    /**
     * @param exchange 交换机
     * @param router 路由
     * @param msg 消息
     * @description: 发布消息
     * @return: java.lang.Boolean
     * @author yongchen
     * @date: 2021/8/20 17:41
     */
    Boolean sendMessage(String exchange, String router, String msg);
}
