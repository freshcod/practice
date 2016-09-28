package com.coral.practice.patterns.singlteton;

/**
 * Created by qiuhai on 2016/7/11.
 */
public class Student extends Person {
    private Integer mark;

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mark=" + mark +
                "} " + super.toString();
    }
}
