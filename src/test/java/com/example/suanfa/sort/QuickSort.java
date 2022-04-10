package com.example.suanfa.sort;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 7/25/21 14:21:22
 * @description:
 */
public class QuickSort {


    /**
     * https://blog.csdn.net/elma_tww/article/details/86164674
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private static int partition(int[] arr, int low, int high) {
        //指定左指针i和右指针j
        int i = low;
        int j = high;

        //将第一个数作为基准值。挖坑
        int base = arr[low];

        //使用循环实现分区操作
        while(i < j){
            //1.从右向左移动j，找到第一个小于基准值的值 arr[j]
            while(arr[j] > base && i < j){
                j--;
            }
            //2.将右侧找到小于基准数的值加入到左边的（坑）位置， 左指针想中间移动一个位置i++
            if(i < j){
                arr[i] = arr[j];
                i++;
            }
            //3.从左向右移动i，找到第一个大于等于基准值的值 arr[i]
            while(arr[i] <= base && i < j){
                i++;
            }
            //4.将左侧找到的打印等于基准值的值加入到右边的坑中，右指针向中间移动一个位置 j--
            if(i < j){
                arr[j] = arr[i];
                j--;
            }
        }

        //使用基准值填坑，这就是基准值的最终位置
        arr[i] = base;//arr[j] = y;
        //返回基准值的位置索引
        return i; //return j;
    }


    private static void quickSort(int[] arr, int low, int high) {

        if(low < high){//???递归何时结束
            //分区操作，将一个数组分成两个分区，返回分区界限索引
            int index = partition(arr, low, high);
            //对左分区进行快排
            quickSort(arr,low,index-1);
            //对右分区进行快排
            quickSort(arr,index+1, high);
        }

    }

    public static void quickSort(int[] arr) {
        int low = 0;
        int high = arr.length-1;
        quickSort(arr,low,high);
    }

    public static void main(String[] args) {
        //给出无序数组
//        int arr[] = {1, 6, 3, 55, 43, 33, 76};
        int arr[] = {1,2,3,4};

        //输出无序数组
        System.out.println(Arrays.toString(arr));
        //快速排序
        quickSort(arr);
        //partition(arr,0,arr.length-1);
        //输出有序数组
        System.out.println(Arrays.toString(arr));
    }
}