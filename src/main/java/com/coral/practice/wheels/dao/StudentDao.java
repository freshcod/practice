package com.coral.practice.wheels.dao;

import com.coral.practice.wheels.anno.MyAutoired;
import com.coral.practice.wheels.bean.Student;

/**
 * Created by qiuhai on 2016/6/17.
 */
public class StudentDao {
    @MyAutoired(value = "student")
    Student stu;

    public Student getStudent(){
        return this.stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public Student getStu() {
        return stu;
    }
}
