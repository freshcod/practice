package com.coral.practice.framework;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


import java.util.HashMap;
import java.util.Map;


/**
 * Created by qiuhai on 2016/9/23.
 */
public class MyApplicationContext implements ApplicationContextAware{

    private ApplicationContext applicationContext;

    public static Map<String,String> taskMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        initServiceMapper();
    }


    public void initServiceMapper(){
        Map<String,Object> map = applicationContext.getBeansWithAnnotation(MyServiceAnno.class);
        for(String key:map.keySet()){
            Object object = map.get(key);
            Class<?> clazz = object.getClass();
            MyApplicationContext.taskMap.put(key,clazz.getName());
        }
    }
}
