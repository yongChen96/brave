package com.cloud.brave.mq.config;

import com.cloud.brave.core.constant.AmqpConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yongchen
 * @description: MQ配置类
 * @date 2021/8/20 17:32
 */
@Configuration
public class BraveMqConfig {

    @Bean
    public Queue braveDirectQueue() {
        /**
         * 1、name:    队列名称
         * 2、durable: 是否持久化
         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
         * */
        return new Queue(AmqpConstants.RABBITMQ_BRAVE_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange braveDirectExchange() {
        //Direct交换机
        return new DirectExchange(AmqpConstants.RABBITMQ_BRAVE_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindingDirect(){
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(braveDirectQueue())
                //绑定交换机
                .to(braveDirectExchange())
                //设置匹配键
                .with(AmqpConstants.RABBITMQ_BRAVE_DIRECT_ROUTER);
    }
}
