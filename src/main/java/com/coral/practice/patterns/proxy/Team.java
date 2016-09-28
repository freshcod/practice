package com.coral.practice.patterns.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by qiuhai on 2016/8/12.
 */
public class Team {

    public static void main(String[] args){
        BasketBallPlayer basketBallPlayer = new BasketBallPlayer();
        FootBallPlayer footBallPlayer = new FootBallPlayer();
//        Agent agent = new Agent(basketBallPlayer);
////        Agent agent = new Agent(footBallPlayer);
//        agent.play();


        Player player = (Player)Proxy.newProxyInstance(footBallPlayer.getClass().getClassLoader(),
                footBallPlayer.getClass().getInterfaces(),new DynamicProxy(footBallPlayer));
        player.play();
    }
}
