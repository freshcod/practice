package com.coral.practice.web.controller;

import com.coral.practice.framework.MyApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by qiuhai on 2016/9/19.
 */
@RestController
public class HelloWorldController {


    @RequestMapping("/hello/{serviceName}")
    public String hello(@PathVariable String serviceName){
        Map<String,String> taskMap = MyApplicationContext.taskMap;
        String className = taskMap.get(serviceName);
        try {
            Class<?> clazz = Class.forName(className);
            Object object = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("excuteTask",new Class[0]);
            method.invoke(object,new Object[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "hello world !!!!!";
    }
}
