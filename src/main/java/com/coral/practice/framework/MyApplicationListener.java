package com.coral.practice.framework;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;

/**
 * Created by qiuhai on 2016/9/26.
 */
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String,Object> objectMap = event.getApplicationContext().getBeansWithAnnotation(MyServiceAnno.class);
        System.out.println("event = [" + objectMap + "]");
        System.out.println("++++++");

    }
}
