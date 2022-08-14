package com.example.suanfa.sort;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author: luozijian
 * @date: 6/28/21 15:49:40
 * @description:
 */
public class MergeArrayTest {

    @Test
    public void merge(){

        int[] num1 = {4,5,6,0};
        int[] num2 = {1};
        merge(num1, 3, num2, 1);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int end = m+n-1;
        while(j >= 0){
            nums1[end--] = (i >= 0 && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        }

    }
}