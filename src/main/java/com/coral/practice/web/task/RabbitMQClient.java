package com.coral.practice.web.task;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by qiuhai on 2016/8/24.
 */
@Component
public class RabbitMQClient {

    @RabbitListener(queues = "test_queue")
    public void getMessage1(String msg){
        System.out.println("111+++"+msg+"+++");
    }


}
