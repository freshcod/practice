package com.coral.practice.algorithm;

/**
 * Created by qiuhai on 2016/7/27.
 * https://leetcode.com/problems/spiral-matrix-ii/
 * 旋转矩阵
 */
public class Solution {
    public int[][] generateMatrix(int n) {
        if (n < 0) {
            n = 0;
        }

        int[][] matrix = new int[n][n];
        if (n == 0) {
            return matrix;
        }

        int counter = 1;

        //左右上下四个边界
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        int i;
        while (true) {

            //上边，自左至右
            for (i = left; i <= right; i++) {
                matrix[top][i] = counter++;
            }
            if (++top > bottom) {
                break;
            }

            //右边，自上至下
            for (i = top; i <= bottom; i++) {
                matrix[i][right] = counter++;
            }
            if (left > --right) {
                break;
            }

            //下边，自右至左
            for (i = right; i >= left; i--) {
                matrix[bottom][i] = counter++;
            }
            if (top > --bottom) {
                break;
            }

            //左边，自下至上
            for (i = bottom; i >= top; i--) {
                matrix[i][left] = counter++;
            }
            if (++left > right) {
                break;
            }
        }

        return matrix;

    }


}
