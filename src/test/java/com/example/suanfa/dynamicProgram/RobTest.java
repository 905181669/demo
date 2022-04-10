package com.example.suanfa.dynamicProgram;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author: luozijian
 * @date: 7/4/21 15:15:41
 * @description: 打家劫舍
 */
public class RobTest {


    @Test
    public void test(){

        int[] rob = {1,2,3,1};
        System.out.println(rob(rob));

    }
    /**
     *
     *
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
     * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
     * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0; //第一家不偷
        dp[0][1] = nums[0]; //第一家偷

        for(int i = 1; i < nums.length; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]); //这家不偷=上家偷与上家不偷的最大值
            dp[i][1] = dp[i-1][0] + nums[i]; //这家偷=上家不偷+这家偷
        }

        return Math.max(dp[nums.length-1][0], dp[nums.length-1][1]);


    }
}