package com.coral.practice.framework;


import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by qiuhai on 2016/9/23.
 */
@Component
@MyServiceAnno
public class TestAnno implements ITaskJob{



    @Override
    public void excuteTask() {
        System.out.println("excute task ==> test!!!!");
    }


    public static void main(String[] args) {
        Assert.notNull("");
    }
}
