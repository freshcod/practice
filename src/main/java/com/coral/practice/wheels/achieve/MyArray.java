package com.coral.practice.wheels.achieve;

import com.coral.practice.wheels.service.Function;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuhai on 2016/7/29.
 */
public class MyArray<T> {

    private List<T> list=new ArrayList<T>();

    public void add(T t){
        list.add(t);
    }

    @Override
    public String toString(){
     return list.toString();
    }

    public MyArray<T> map(Function function){
        if(function==null){
            return null;
        }
        MyArray<T> newList = new MyArray<T>();
        for(T t:list){
            newList.add((T)function.execute(t));
        }
        return newList;
    }

    public static void main(String [] args){
        MyArray<Integer> myArray = new MyArray<>();
        myArray.add(1);
        myArray.add(2);
        myArray.add(3);
        System.out.println(myArray);
        MyArray<Integer> newArray = myArray.map(new Function() {

            @Override
            public Integer execute(Object object) {
                return Integer.parseInt(object.toString())*2;
            }
        });
        System.out.println(newArray);

    }
}
