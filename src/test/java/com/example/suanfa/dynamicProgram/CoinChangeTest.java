package com.example.suanfa.dynamicProgram;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 7/1/21 08:33:19
 * @description:
 */
public class CoinChangeTest {


    @Test
    public void test(){

        int[] coins = {1,2,5};

        System.out.println(coinChange(coins, 11));
//        System.out.println(coinChange1(coins, 11));
    }


    public int coinChange(int[] coins, int amount) {

        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];

    }



    int res = Integer.MAX_VALUE;

    public int coinChange1(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }

        findWay(coins,amount,0);

        // 如果没有任何一种硬币组合能组成总金额，返回 -1。
        if(res == Integer.MAX_VALUE){
            return -1;
        }
        return res;
    }

    public void findWay(int[] coins, int amount, int count){
        if(amount < 0){
            return;
        }

        if(amount == 0){
            res = Math.min(res, count);
        }

        for(int i = 0 ; i < coins.length; i++){
            findWay(coins, amount - coins[i], count+1);
        }
    }


}