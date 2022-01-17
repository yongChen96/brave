package com.cloud.brave.mq.config;

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
            /**
             * @description: 生产者消息确认回调 Confirm
             * @param correlationData 对象内部只有一个 id 属性，用来表示当前消息的唯一性
             * @param ack 消息投递到broker的状态，true表示成功，false表示失败
             * @param cause 投递失败的原因
             * @return: void
             * @author yongchen
             * @date: 2022/1/17 10:38
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("MQ消息发送成功，消息主体：{}，确认状态：{}，造成原因：{}", correlationData, ack, cause);
            }
        });
        //消息发送失败回调
        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            /**
             * @description: 队列消息确认回退机制
             * @param returnedMessage （内有五个参数，message：消息体，replyCode：响应code，replyText：响应内用，exchange：交换机，routingkey：队列路由）
             * @return: void 
             * @author yongchen
             * @date: 2022/1/17 10:42
             */
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
