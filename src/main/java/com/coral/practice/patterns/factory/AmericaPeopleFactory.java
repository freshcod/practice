package com.coral.practice.patterns.factory;

/**
 * Created by qiuhai on 2016/9/23.
 */
public class AmericaPeopleFactory implements AbstractPeopleFactory {
    @Override
    public People createPeople() {
        return new AmericaPeople();
    }
}
