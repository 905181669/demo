package com.example.suanfa.array;

import org.junit.Test;

import java.util.*;

/**
 * @author: luozijian
 * @date: 7/27/21 11:32:33
 * @description: 二分查找
 *
 * https://leetcode-cn.com/problems/binary-search/
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 *
 */
public class BinarySearch {


    /**
     * 写法一，在左闭右闭的区间里，也就是[left, right] （这个很重要非常重要）
     * @return
     */
    public static int search1(int[] array, int target){

        int left = 0;
        int right = array.length - 1;
        while(left <= right){
            int middle = left + (right - left) / 2;
            if(target < array[middle]){
                right = middle - 1;
            }else if(target > array[middle]){
                left = middle + 1;
            }else {
                return middle;
            }
        }

        return -1;
    }


    /**
     * 左闭右开的区间里，即：[left, right)
     * @param nums
     * @param target
     * @return
     */
    public static int search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 定义target在左闭右开的区间里，即：[left, right)
        while (left < right) { // 因为left == right的时候，在[left, right)是无效的空间，
                               // 所以使用 <, 下一个查询区间不会去比较nums[middle]
//            int middle = left + ((right - left) >> 1);
            int middle = left + ((right - left) / 2);
            if (target < nums[middle]) {
                right = middle; // target 在左区间，在[left, middle)中
            } else if (target > nums[middle]) {
                left = middle + 1; // target 在右区间，在[middle + 1, right)中
            } else { // nums[middle] == target
                return middle; // 数组中找到目标值，直接返回下标
            }
        }
        // 未找到目标值
        return -1;
    }


    @Test
    public void test(){

        int[] array = {1,2,3,4,7,9,10};
        System.out.println(search1(array, 3));
//        System.out.println(search2(array, 0));

    }

    @Test
    public void test1(){
        int[] array = {3,5,1};
        System.out.println(search(array, 1));

    }

    public int search(int[] nums, int target) {
        //首先找到旋转处的index
        int len = nums.length;
        int flag = 0;
        for(int i = 0; i < len-1; i++) {
            if(nums[i] > nums[i+1]) {
                flag = i;
                break;
            }
        }

        int middle = 0;
        int min = 0;
        int max = flag;
        while(min <= max){
            middle = min + (max-min)/2;
            if(nums[middle] == target) {
                return middle;
            }else if(nums[middle] < target) {
                min = middle+1;
            }else if(nums[middle] > target) {
                max = middle-1;
            }
        }

        min = flag + 1;
        max = nums.length-1;
        while(min <= max){
            middle = min + (max-min)/2;
            if(nums[middle] == target) {
                return middle;
            }else if(nums[middle] < target) {
                min = middle+1;
            }else if(nums[middle] > target) {
                max = middle-1;
            }
        }
        return -1;
    }

    public String defangIPaddr(String address) {

        Queue queue = new LinkedList<>();
        queue.add(1);
        int[] a = new int[queue.size()];


        return address.replace(".", "[.]");
    }
}