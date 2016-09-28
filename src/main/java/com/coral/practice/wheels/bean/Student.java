package com.coral.practice.wheels.bean;

/**
 * Created by qiuhai on 2016/6/17.
 */
public class Student {
    private String userName;
    private Integer userAge;
    private String gender;
    private Integer mark;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", gender='" + gender + '\'' +
                ", mark=" + mark +
                '}';
    }


}
