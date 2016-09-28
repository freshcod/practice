package com.coral.practice.patterns.proxy;

/**
 * Created by qiuhai on 2016/8/12.
 */
public class Agent implements Player {

    private Player player;

    /**
     * 静态代理,只能代理BasketBallPlayer
     */
    public Agent(){
        this.player = new BasketBallPlayer();
    }

    /**
     * 此构造方法可实现动态代理(所有实现PLAYER接口的类)
     * @param player
     */
    public Agent(Player player) {
        this.player = player;
    }

    public void signTeam(){
        System.out.println("签约球队");
    }

    public void meetFans(){
        System.out.println("会见粉丝");
    }
    @Override
    public void play() {
        signTeam();
        player.play();
        meetFans();
    }
}
