package com.cloud.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author yongchen
 * @description: MQ模板设置
 * @date 2021/8/23 9:55
 */
@Slf4j
@Configuration
public class RabbitTemplateConfig {

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        // 消息发送成功回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                log.info("MQ消息发送成功，消息主体：{}，确认状态：{}，造成原因：{}", correlationData, b, s);
            }
        });
        //消息发送失败回调
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                log.info("MQ消息发送失败，消息主体：{}，响应编码：{}，响应文本：{}，交换机：{}，路由键：{}",
                        returnedMessage.getMessage(),
                        returnedMessage.getReplyCode(),
                        returnedMessage.getReplyText(),
                        returnedMessage.getExchange(),
                        returnedMessage.getRoutingKey());
            }
        });
        return rabbitTemplate;
    }
}
