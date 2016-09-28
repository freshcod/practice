package com.coral.practice.patterns.factory;

/**
 * Created by qiuhai on 2016/9/23.
 */
public class ChinesePeopleFactory implements AbstractPeopleFactory {
    @Override
    public People createPeople() {
        return new ChinesePeople();
    }
}
