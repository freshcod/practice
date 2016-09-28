package com.coral.practice.patterns.factory;

/**
 * Created by qiuhai on 2016/9/23.
 * 简单工厂模式
 */

public class SimplePeopleFactory {

    public static People createPeople(Integer type){
        if(type == 1){
            return new AmericaPeople();
        }else if(type == 2){
            return new ChinesePeople();
        }
        return null;
    }

    public static void main(String[] args){
        People people = SimplePeopleFactory.createPeople(1);
        people.say();
        people = SimplePeopleFactory.createPeople(2);
        people.say();

        AbstractPeopleFactory abstractPeopleFactory = new AmericaPeopleFactory();
        people = abstractPeopleFactory.createPeople();
        people.say();

        abstractPeopleFactory = new JapanesePeopleFactory();
        people = abstractPeopleFactory.createPeople();
        people.say();
    }

}
