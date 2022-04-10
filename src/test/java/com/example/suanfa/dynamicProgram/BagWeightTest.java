package com.example.suanfa.dynamicProgram;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 7/4/21 13:55:46
 * @description:
 *
 * 背包问题
 */
public class BagWeightTest {

    @Test
    public void test(){
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        System.out.println(testBagProblem1(weight, value, 4));

        System.out.println(testBagProblem2(weight, value, 4));
    }


    /**
     *
     * 二维数组解法
     * 递推公式:dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
     * @param weight 物品重量
     * @param value 物品价值
     * @param bagWeight 目标背包的所能承受的重量
     * @return
     */
    public int testBagProblem1(int[] weight, int[] value, int bagWeight){
        int[][] dp = new int[weight.length + 1][bagWeight + 1];
        // 初始化
//        for (int j = bagWeight; j >= weight[0]; j--) {
//            dp[0][j] = dp[0][j - weight[0]] + value[0];
//        }

        //初始化只选物品i时，背包容量从0-4的最大价值0,15,15,15,15
        for(int j = 0; j <= bagWeight; j++){
            if(j - weight[0] >= 0){
                dp[0][j] = value[0];
            }
        }


        // weight数组的大小 就是物品个数
        for(int i = 1; i < weight.length; i++) { // 遍历物品
            for(int j = 0; j <= bagWeight; j++) { // 遍历背包容量
                if (j < weight[i]){ //背包容量<当前物品重量，则dp[i][j] = 不选当前物品的最大价值
                    dp[i][j] = dp[i - 1][j];
                } else{ //如果当前背包容量>当前物品重量，则dp[i][j] = 取不选物品i和选物品i的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        return dp[weight.length - 1][bagWeight];

    }

    /**
     * 一维数组解法
     * @param weight
     * @param value
     * @param bagWeight
     *
     * 递推公式：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
     *
     * @return
     */
    public int testBagProblem2(int[] weight, int[] value, int bagWeight){

        //定义dp[j]为 容量为j的背包所背的最大价值
        int[] dp = new int[bagWeight + 1];
        dp[0] = 0;
        for(int i = 0; i < weight.length; i++){ //遍历每件物品
            for(int j = bagWeight; j >= weight[i]; j--){ //按背包重量倒序遍历，倒叙遍历是为了保证物品i只被放入一次!

                //初始值为0，正整数，覆盖
                dp[j] = Math.max(dp[j], dp[ j - weight[i]] + value[i]);
            }
        }

        System.out.println(Arrays.toString(dp));
        return dp[bagWeight];

    }


    @Test
    public void testCanPartition(){
        int[] nums = {1,5,11,5};
        System.out.println(canPartition(nums));
    }
    /**
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,5,11,5]
     * 输出：true
     * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
     *
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {

        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;

        //套到本题，dp[i]表示 背包总容量是i，最大可以凑成i的子集总和为dp[i]
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = target; nums[i] <= j; j--) {
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];

    }



    @Test
    public void testCanPartition1(){

        int[] nums = {1,5,11,5};
        System.out.println(canPartition1(nums));
    }

    public boolean canPartition1(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;

        //套到本题，dp[i]表示 背包总容量是i，最大可以凑成i的子集总和为dp[i]
        int[] dp = new int[target + 1];
        dp[0] = 0;

        for(int i = 0; i < len; i++){ //最外层遍历nums

            for(int j = target; j >= nums[i]; j-- ){ // j>=nums[i] 如果背包容量j可以装得下nums[i]，则有如下表达式
                //dp[j]取dp[j]和累加最大值
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }

        }

        return dp[target] == target;
    }



}