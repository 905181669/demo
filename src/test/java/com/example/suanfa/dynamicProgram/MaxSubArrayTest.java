package com.example.suanfa.dynamicProgram;

/**
 * @author: luozijian
 * @date: 3/9/21 17:50:19
 * @description:
 */
public class MaxSubArrayTest {


    public static void main(String[] args) {
        int[] nums = {1,2};
        System.out.println(maxSubArray(nums));
//        System.out.println(maxSubArray1(nums));
    }

    /**
     * 动归5步曲：
     * 1.确定dp数组(dp table)以及下标的含义
     * 2.确定递推公式，即dp[i] 与 dp[i-1]或dp[i-2]的关系式
     * 3.dp数组的初始化
     * 4.确定遍历顺序
     * 5.举例推导dp数组
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        //确定dp数组(dp table)以及下标的含义
        int [] dp = new int[nums.length];

        //初始化dp
        dp[0] = nums[0];
        int max = nums[0];

        //确定遍历顺序
        for(int i = 1; i < nums.length; i++){

            //确定递推公式
            dp[i] = Math.max(0, dp[i-1]) + nums[i];
            max = Math.max(max, dp[i]);

        }

        return max;

    }

    /**
     * 关键点是记录一个最大值和dp[]
     *
     * @param num
     * @return
     */
    public static int maxSubArray1(int[] num) {
        int length = num.length;
        int[] dp = new int[length];
        //边界条件
        dp[0] = num[0];
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            //转移公式
            dp[i] = Math.max(dp[i - 1], 0) + num[i];
            //记录最大值
            max = Math.max(max, dp[i]);
        }
        return max;
    }


}
