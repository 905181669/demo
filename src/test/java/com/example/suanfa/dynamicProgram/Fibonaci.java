package com.example.suanfa.dynamicProgram;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 5/3/21 11:49:11
 * @description:
 */
public class Fibonaci {


    @Test
    public void test(){
//        System.out.println(fibonaci1(6));

//        System.out.println(fibonaci2(6));

//        System.out.println(climbStairs(5));
//        System.out.println(climbStairs1(5, 2));

        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs(cost));
    }


    public int fibonaci1(int n){
        if(n <= 2){
            return 1;
        }

        return fibonaci1(n-1) + fibonaci1(n-2);

    }


    /**
     * 动态规划
     * @param n
     * @return
     */
    public int fibonaci2(int n){

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;


        for(int i = 2; i <=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }


    /**
     * 爬楼梯，每次走1或2步
     * @param n
     * @return
     */
    public int climbStairs(int n){

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(Arrays.toString(dp));

        return dp[n];

    }


    /**
     * 每次最多m个台阶
     * @param n
     * @return
     */
    public int climbStairs1(int n, int m) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 把m换成2，就可以AC爬楼梯这道题
                if (i - j >= 0) {
                    dp[i] += dp[i - j];
                }
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    public int minCostClimbingStairs(int[] cost) {

        //dp[i]的定义:到达第i个台阶所花费的最少体力为dp[i]
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i = 2; i < cost.length; i++){
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }

        System.out.println(Arrays.toString(dp));
        return Math.min(dp[dp.length-1], dp[dp.length-2]);

    }


    /**
     * 尾递归
     */
    @Test
    public void testFibonacciTailRecursion(){

        System.out.println(fibonacciTailRecursion(6));
    }

    public static long fibonacciTailRecursion(long index) {
        return fibonacciTailRecursion(index, 0, 1);
    }

    public static long fibonacciTailRecursion(long index, int curr, int next) {
        if (index == 0) {
            return curr;
        } else {
            return fibonacciTailRecursion(index - 1, next, curr + next);
        }


    }


}