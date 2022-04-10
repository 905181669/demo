package com.example.suanfa.dynamicProgram;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/** 动态规划经典题目：
 * 在数字三角形中寻找一条从顶部到底部的路径，使得路径上的数字和最大。每次只能往下或右下走一步
 * @author: luozijian
 * @date: 3/9/21 08:40:22
 * @description:
 */
public class TriangleTest {

    public static void main(String[] args) {


        System.out.println("最大路径值：" + getMax());
//        System.out.println(funcA(3));
    }

    public static int getMax(){

        int[][] D = createArray(3, 3);
        int l = 2;              //n表示层数，从0开始计数
        int i = 0; int j = 0;
        int maxSum = getMaxSum(D, l, i,j);
        return maxSum;
    }

    public static int getMaxSum(int[][] array,int l,int i,int j){

        int cur = array[i][j];
        System.out.println("cur:" + cur);
        if(i == l){
            return cur;
        }

        int a = getMaxSum(array, l,i+1, j);
        int b = getMaxSum(array, l,i+1,j+1);

        return Math.max(a, b) + cur;
    }


    public static int[][] createArray(int row, int column){

        int [] nums = {1,2,3,4,5,6,7,8,9,0};
        int numsIndex = 0;

        int[][] array=new int[row][column];
        for(int i=0,j=array.length;i<j;i++){
            for(int h=0,k=array[i].length;h<k;h++){
//                array[i][h]=new Random().nextInt(10); //赋值：100以内的随机数

                array[i][h] = nums[numsIndex++];
            }
        }

        //遍历
        for(int i=0,j=array.length;i<j;i++){
            System.out.println();
            for(int h=0,k=array[i].length;h<k;h++){
                System.out.print(array[i][h]+"\t");
            }
        }

        System.out.println("\n");
        return array;
    }


    public static int funcA(int n){

        if(n > 1){
            int a = n + funcA(n-1);
            System.out.println(n);
            return a;
        }
        else{
            System.out.println(n);
            return 1;
        }

    }


    /**
     * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
     *
     * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标相同或者等于
     * 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，
     * 那么下一步可以移动到下一行的下标 i 或 i + 1 。
     *
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
            for (int j = 1; j < i; ++j) {
                f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
            }
            f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
        }
        int minTotal = f[n - 1][0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[n - 1][i]);
        }
        return minTotal;
    }


}
