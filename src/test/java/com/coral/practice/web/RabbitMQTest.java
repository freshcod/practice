package com.coral.practice.web;

import com.PracticeApplication;
import com.coral.practice.config.MQService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.UUID;

/**
 * Created by qiuhai on 2016/8/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PracticeApplication.class)
@WebAppConfiguration
public class RabbitMQTest {

    @Autowired
    MQService mqService;

    @Test
    public void  testSendMQMsg(){
        String exchange="test.topic";
        String routeKey = "test_queue";
        mqService.sendToMQ(exchange,routeKey,"hello world");
    }

}
