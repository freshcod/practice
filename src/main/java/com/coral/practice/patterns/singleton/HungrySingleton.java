package com.coral.practice.patterns.singleton;

/**
 * Created by qiuhai on 2016/6/30.
 * 饿汉单例模式
 */
public class HungrySingleton {
    private static HungrySingleton INSTANCE = new HungrySingleton();


    private HungrySingleton(){
        if(INSTANCE!=null)
            throw new RuntimeException("单例模式被攻击!!!");

    }

    public static HungrySingleton getINSTANCE(){
        return INSTANCE;
    }
}
