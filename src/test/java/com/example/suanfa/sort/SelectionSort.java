package com.example.suanfa.sort;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 6/20/21 20:12:21
 * @description:
 * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。
 * 唯一的好处可能就是不占用额外的内存空间了吧。
 * 理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
 */
public class SelectionSort {

    /**
     * 选择排序（不稳定）
     * 选择排序，顺序选择一个当前位置curr，然后在剩下的各个位置中找到最小值的位置min，
     * 选择剩下最小值的位置min并和当前位置curr交换
     *
     * 大O，n的2次方/2次比较
     * n次交换
     * @param args
     */
    public static void main(String[] args) {

        int[] list = {100,333,4,7,1,5,2,10,44,32};

        for(int i = 0; i < list.length; i++){

            int min = i;
            for(int j = i + 1; j < list.length; j++){
                if(list[min] > list[j]){
                    min = j; //从剩余的数组中选出最小的
                }
            }

            int tmp = list[i];
            list[i] = list[min];
            list[min] = tmp;

        }

        System.out.println(Arrays.toString(list));
    }
}