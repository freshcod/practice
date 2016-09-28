package com.coral.practice.patterns.singleton;

/**
 * Created by qiuhai on 2016/6/30.
 * 懒汉单例模式
 */
public class LazySingleton {

    private static LazySingleton INSTANCE = null;

    //防止单例被反射攻击,让构造函数只被调用一次
    private static boolean flag = false;

    private LazySingleton(){
        if(!flag){
            flag = !flag;
        }else {
            throw new RuntimeException("单例模式被攻击!!!");
        }
    }

    public static LazySingleton getINSTANCE(){
        if(INSTANCE == null) INSTANCE=new LazySingleton();
        return INSTANCE;
    }

}
