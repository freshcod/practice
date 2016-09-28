package com.coral.practice.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qiuhai on 2016/6/21.
 */
public class ThreeSumAlgorithm {
    public List<List<Integer>> threeSum(Integer[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                int x = 0-nums[i]-nums[j];
                int r = Arrays.binarySearch(nums,x);
                if(r<=j) continue;
                System.out.println("i: "+i+" j: "+j+" r: "+r);
                List<Integer> temp = Arrays.asList(new Integer[]{nums[i],nums[j],x});
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args){
        ThreeSumAlgorithm threeSum = new ThreeSumAlgorithm();
        Integer[] nums = new Integer[]{-1, 0, 1, 2, -1, -4};
        System.out.println(Arrays.binarySearch(nums,new Integer(2)));
        List<List<Integer>> result = threeSum.threeSum(nums);
        System.out.println(result);
    }

}