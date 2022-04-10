package com.example.suanfa.dynamicProgram;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 6/30/21 13:38:41
 * @description:
 */
public class MaxProfitTest {


    @Test
    public void testMaxProfix(){

        int[] prices = {7,1,5,3,6,7};

        System.out.println(maxProfit(prices));
        System.out.println(maxProfix1(prices));

    }

    /**
     * 动态规划
     * @param prices
     * @return
     */
    public  int maxProfit(int[] prices) {

        int max = 0;
        int[] dp = new int[prices.length];
        dp[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.min(dp[i-1], prices[i]);
            max = Math.max(prices[i] - dp[i], max);

        }
        return max;
    }


    /**
     * 暴力解决，
     * @param prices
     * @return
     */
    public int maxProfix1(int[] prices){
        int max = 0;

        for(int i = 0; i < prices.length; i++){

            for(int j = i + 1; j < prices.length; j++){
                int profix = prices[j] - prices[i];
                if(profix > max){
                    max = profix;
                }

            }

        }

        return max;
    }
}