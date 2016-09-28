package com.coral.practice.algorithm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 * Created by qiuhai on 2016/7/28.
 * https://leetcode.com/problems/peeking-iterator/
 */
public class PeekingIterator implements Iterator<Integer> {

    private Integer middle = null;
    private Iterator<Integer> iterator;

    public PeekingIterator(Iterator<Integer> iterator){
        this.iterator=iterator;
    }

    public Integer peek(){
        if(middle==null){
            middle = iterator.next();
        }
        return middle;
    }


    @Override
    public Integer next() {
        if(middle!=null){
            Integer temp = middle;
            middle = null;
            return temp;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        if(middle!=null) return true;

        return iterator.hasNext();
    }

    public static void main(String [] args){
        List<Integer> test = Arrays.asList(new Integer[]{1,2,3,4});
        PeekingIterator peekingIterator = new PeekingIterator(test.iterator());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.peek());
        System.out.println(peekingIterator.next());
        System.out.println(peekingIterator.next());
    }
}