package com.example.suanfa.bit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author: luozijian
 * @date: 2022/3/9
 * @description:
 */
public class BitTest {

    public static void main(String[] args) {

        int[] nums = {0, 2, 1};
        System.out.println(missingNumber(nums));

        List<Integer> list = new ArrayList<>();
        list.sort(Comparator.comparingInt(e->e));
    }

    public static int missingNumber(int[] nums) {
        int xor = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        return xor;

    }

}