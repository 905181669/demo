package com.example.suanfa.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: luozijian
 * @date: 2022/7/26
 * @description: 单调队列
 * 输入一个数组nums和一个正整数k，有一个大小为k的窗口在nums上从左往右滑动，请输出每次滑动时窗口中的最大值
 */
public class MonotonicQueueTest {

    @Test
    public void test(){
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow(nums, 3)));
    }


    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue monotonicQueue = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if (i < k-1) {
                monotonicQueue.add(nums[i]);
            } else {
                monotonicQueue.add(nums[i]);
                res.add(monotonicQueue.max());
                monotonicQueue.pop(nums[i-k+1]);
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    /**
     * 单调队列实现（使用双端队列）
     */
    private class MonotonicQueue{

        LinkedList<Integer> queue = new LinkedList<>();

        /**
         * 往单调队列中添加元素，如果前边的元素比添加的元素小，则移除前边的元素,
         * 这样就可以形成一个单调递减的队列
         * @param n
         */
        public void add(int n) {
            while(!queue.isEmpty() && queue.getLast() < n){
                queue.pollLast();
            }
            queue.addLast(n);
        }

        /**
         * 返回队列中的第一个元素就是最大的元素
         * @return
         */
        public int max() {
            return queue.getFirst();
        }

        /**
         * 删除队列的第一个元素
         * @param n
         */
        public void pop(int n){
            if(n == queue.getFirst()) {
                queue.pollFirst();
            }
        }
    }
}