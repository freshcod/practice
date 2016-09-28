package com.coral.practice.config;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RabbitTemplate getRabbitTemplate(){
        return rabbitTemplate;
    }

    /**
     *
     * @param exchange
     * @param routingKey
     * @param message
     * @return
     */
    public String sendToMQ(String exchange,String routingKey,Object message){
        String id = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(id);
        getRabbitTemplate().convertAndSend(exchange, routingKey, message, correlationId);
        return id;
    }

    /**
     *
     * @param routingKey
     * @param message
     * @return
     */
    public String sendToMQ(String routingKey,Object message){
        String id = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(id);
        getRabbitTemplate().convertAndSend(routingKey, message, correlationId);
        return id;
    }

}
