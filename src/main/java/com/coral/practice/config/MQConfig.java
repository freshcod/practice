package com.coral.practice.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by qiuhai on 2016/8/24.
 */
@Configuration
public class MQConfig {

    @Autowired
    private RabbitProperties properties;

    @Bean
    public CachingConnectionFactory connectionFactory(){

       CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
       connectionFactory.setHost(properties.getHost());
       connectionFactory.setPort(properties.getPort());
       connectionFactory.setUsername(properties.getUsername());
       connectionFactory.setPassword(properties.getPassword());
       connectionFactory.setVirtualHost(properties.getVirtualHost());
       connectionFactory.setPublisherConfirms(true);
       return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        return rabbitTemplate;
    }

    @Bean
    public MQService mqService(){
        return new MQService();
    }



}
