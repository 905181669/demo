package com.example.suanfa.dynamicProgram;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 7/3/21 16:31:41
 * @description:
 */
public class IntegerBreak {

    @Test
    public void test(){
        System.out.println(integerBreak(10));
//        System.out.println(integerBreak1(10));

    }

    public int integerBreak(int n) {

        if(n < 2){
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for(int i = 3; i <= n; i++){

            for(int j = 1; j < i; j++){
                int temp = Math.max((i-j) * j, dp[i-j] * j);
                System.out.println("i = " + i + " j=" + j +  "  (i-j)*j = " + ((i-j) * j) + "  dp[i-j] * j=" + (dp[i-j] * j));
//                System.out.println("i = " + i + " j=" + j + " temp=" + temp + " dp[i]=" + dp[i]);
                //在遍历过程中，dp[i]选最大值那个
                dp[i] = Math.max(temp, dp[i]);
            }

        }

        System.out.println(Arrays.toString(dp));
        //[0, 0, 1, 2, 4, 6, 9, 12, 18, 27, 36]
        //[0, 0, 1, 2, 4, 6, 8, 10, 12, 14, 16]
        return dp[n];

    }

    public int integerBreak1(int n) {

        //dp是第i个数的子的最大乘积
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int curMax = 0;
            for (int j = 1; j < i; j++) {

                /**
                 *将 i 拆分成 j 和 i-j 的和，且 i-j不再拆分成多个正整数，此时的乘积是
                 *   j×(i−j)；
                 *
                 * 将 i 拆分成 j 和 i-j 的和，且 i-j 继续拆分成多个正整数，此时的乘积是
                 * j×dp[i−j]。
                 * 因此dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j] ))
                 *
                 */

                curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }

        System.out.println(Arrays.toString(dp));
        return dp[n];
    }


}