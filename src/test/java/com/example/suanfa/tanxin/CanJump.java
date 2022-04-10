package com.example.suanfa.tanxin;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 3/17/21 09:51:26
 * @description:
 */
public class CanJump {
    public static void main(String[] args) {

        int[] nums = {1,2,1,0,5};
        canJump(nums);
    }

    public static boolean canJump(int[] nums) {

        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;

    }


    @Test
    public void test1() {

    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = g.length - 1;
        int result = 0;
        for(int i = g.length; i >= 0; i--) {
            if(index >= 0 && s[index] >= g[i]) {
                result++;
                index--;
            }
        }

        return result;
    }
}
