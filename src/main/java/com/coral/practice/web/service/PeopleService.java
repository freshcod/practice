package com.coral.practice.web.service;

import org.springframework.stereotype.Service;

/**
 * Created by qiuhai on 2016/8/30.
 */
@Service
public class PeopleService{

    public String sleep(){
        System.out.println("睡觉");
        return "ok";
    }
}
