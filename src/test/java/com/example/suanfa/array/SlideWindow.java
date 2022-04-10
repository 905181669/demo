package com.example.suanfa.array;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 7/28/21 10:55:50
 * @description:
 *
 * 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
 * 并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SlideWindow {


    @Test
    public void test(){

        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen1(7, nums));
        System.out.println(minSubArrayLen2(7, nums));
    }


    /**
     * 暴力
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen1(int target, int[] nums) {

        int result = Integer.MAX_VALUE;
        int subLen = 0;
        int sum = 0;
        for(int i = 0; i < nums.length; i++){

            sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                if(sum >= target){
                    subLen = j - i + 1;
                    result = Math.min(subLen, result);
                    break;
                }
            }

        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }


    /**
     * 滑动窗口解法
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int target, int[] nums) {

        int result = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for(int right = 0; right < nums.length; right++){
            sum += nums[right];
            while(sum >= target){

                //符合判断条件则重新计算result
                result = Math.min(result, right-left+1);

                //然后减去left位置的数看看条件是否仍然成立
                sum -= nums[left++];
            }

        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }


}