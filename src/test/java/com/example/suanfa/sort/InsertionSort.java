package com.example.suanfa.sort;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 6/24/21 11:53:33
 * @description:
 * 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        //测试80万个数据的排序
        int len = 50;
        int[] arr = new int[len];
        for (int index = 0; index < len; index++) {
            arr[index] = (int)(Math.random() * len);
        }

        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序（稳定）
     * 时间复杂度n^2
     *
     * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，
     * 对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，
     * 通常采用in-place排序（即只需用到O(1)的额外空间的排序），因而在从后向前扫描过程中，
     * 需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
     * @param arr
     */
    public static void sort(int[] arr){

        for (int i = 1; i < arr.length; i++) { //每次i循环，把<=i的位置排好序

            for(int j = i; j > 0; j--){ //内循环j排好序

                if (arr[j] < arr[j-1]){
                    int temp ;
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;

                }else{
                    //优化，如果arr[j] >= arr[j-1]，说明arr[j]是当前的最大值了，不会比arr[0]...arr[j-1]更小
                    // 并且arr[0]...arr[j-1]都是已经排好序的，因此可以直接结束这次i循环
                    //[0,1,8,12, 9(假设j在这，9和12交换，并且j--) ,20.22]
                    //[0,1,8,9(交换后,再次if判断，发现arr[j]比arr[j-1]大，则结束本次i循环) ,12,20.22]
                    break;
                }
            }
        }

    }
}