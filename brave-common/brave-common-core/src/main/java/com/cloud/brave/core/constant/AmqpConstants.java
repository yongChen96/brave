package com.cloud.brave.core.constant;

/**
 * @author yongchen
 * @description: 队列主题、交换机名称、路由匹配常量类
 * @date 2021/8/20 15:00
 */
public class AmqpConstants {
    /**
     * 队列主题名称
     */
    public static final String RABBITMQ_BRAVE_TOPIC = "brave.topic";
    /**
     * Direct交换机名称
     */
    public static final String RABBITMQ_BRAVE_DIRECT_EXCHANGE = "brave.direct.exchange";
    /**
     * Direct交换机和队列绑定的匹配键
     */
    public static final String RABBITMQ_BRAVE_DIRECT_ROUTER = "brave.direct.router";
}
