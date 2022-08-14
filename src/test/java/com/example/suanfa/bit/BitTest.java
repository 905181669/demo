package com.example.suanfa.bit;

import org.junit.Test;

import java.util.*;

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

    /**
     * 连续n个数的和
     * 求 1 2 ... n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句
     * (A?B:C)。
     * @param n
     * @return
     */
    public int sumNums(int n) {
        // 利用&&的短路特性解决递归出口问题
        boolean b = n>0 && ((n += sumNums(n-1)) > 0);
        return n;
    }

    public int sumNums1(int n){
        if(n < 0) {
            return 0;
        }
        n += sumNums1(n-1);
        return n;
    }

    @Test
    public void testSumNums(){
        System.out.println(sumNums(5));
        System.out.println(sumNums1(5));
    }

    /**
     * 第231题:2的幂 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     * @return
     */
    public boolean isTwoMi(int n){
        return n > 0 && (n & (n-1)) == 0;
    }

    @Test
    public void testisTwoMi(){
        System.out.println(isTwoMi(4));
        
    }

}