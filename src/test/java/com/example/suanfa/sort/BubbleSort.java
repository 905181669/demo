package com.example.suanfa.sort;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 7/8/21 15:13:00
 * @description:
 */
public class BubbleSort {

    public static void main(String[] args) {
        //测试80万个数据的排序
        int len = 50;
        int[] arr = new int[len];
        for (int index = 0; index < len; index++) {
            arr[index] = (int)(Math.random() * len);
        }

        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 冒泡排序（稳定）O(n^2)
     * 最佳情况：T(n) = O(n)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
     *
     * 从数组的后往前排，大的数沉到最后，就像冒泡
     * @param array
     * @return
     */
    public static int[] bubbleSort(int[] array) {
        if (array.length == 0)
            return array;

        for(int i = 0; i < array.length; i++){ //每一轮循环把最大的移动最后
            for(int j = 0; j < array.length - 1 - i; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;

                }
            }
        }
        return array;
    }

    /**
     * 思想：每一轮循环把最大的数放最后
     *
     * @param array
     */
    public static void bubbleSort1(int[] array) {

        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length-1-i; j++) {
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j+1] = temp;
                }
            }
        }
    }
}