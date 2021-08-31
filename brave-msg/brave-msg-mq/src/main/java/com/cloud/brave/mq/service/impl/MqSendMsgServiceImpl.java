package com.cloud.brave.mq.service.impl;

import com.cloud.brave.core.exception.BraveException;
import com.cloud.brave.mq.service.MqSendMsgService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yongchen
 * @description: 消息发布服务
 * @date 2021/8/20 17:30
 */
@Service
@RequiredArgsConstructor
public class MqSendMsgServiceImpl implements MqSendMsgService {

    private final RabbitTemplate rabbitTemplate;

    /**
     * @param exchange 交换机
     * @param router 路由
     * @param msg 消息
     * @description: 发布消息
     * @return: java.lang.Boolean
     * @author yongchen
     * @date: 2021/8/20 17:41
     */
    @Override
    public Boolean sendMessage(String exchange, String router, String msg) {
        try{
            rabbitTemplate.convertAndSend(exchange, router, msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            throw new BraveException("消息发布失败");
        }
    }
}
