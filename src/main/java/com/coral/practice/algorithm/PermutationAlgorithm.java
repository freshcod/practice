package com.coral.practice.algorithm;

import java.util.Arrays;

/**
 * Created by qiuhai on 2016/6/27.
 */
public class PermutationAlgorithm {

    public static void main(String[] args){
        String ss = "sts";
        Integer[] ids = new Integer[100];
        for(int i=0;i<100;i++){
           ids[i] = i;
        }
        System.out.println(Arrays.toString(ids));
    }
}
