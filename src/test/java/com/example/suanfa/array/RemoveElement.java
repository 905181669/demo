package com.example.suanfa.array;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 7/27/21 16:14:52
 * @description:
 *
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 *
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveElement {

    @Test
    public void test(){
        int[] nums = {3,3,4,6,7};
//        removeElement(nums, 3);
        removeElement1(nums, 3);
    }

    public int removeElement(int[] nums, int val) {

        int size = nums.length;
        for(int i = 0;i < size; i++){
            if(nums[i] == val){
                for(int j = i + 1; j < size; j++){
                    nums[j-1] = nums[j];
                }
                size--;
                i--;
            }
        }
        return size;
    }


    /**
     * 双指针
     * @param nums
     * @param val
     * @return
     */
    public int removeElement1(int[] nums, int val) {

        // 快慢指针
        int fastIndex = 0;
        int slowIndex;
        for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;

    }
}