package com.coral.practice.web.service;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * Created by qiuhai on 2016/8/30.
 */
@Aspect
@Component
public class PeopleHelperService {

    @Pointcut("execution(* *.sleep())")
    public void sleepPoint(){
        System.out.println("切入点");
    }

    @Before("sleepPoint()")
    public void beforePoint(){
        System.out.println("脱衣服");
    }

    @After("sleepPoint()")
    public void afterPoint(){
        System.out.println("做梦");
    }

    @AfterReturning("sleepPoint()")
    public void afterReturn(){

        System.out.println("起床");
    }
}
