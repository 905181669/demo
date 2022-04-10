package com.example.suanfa.heap;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 2022/3/20
 * @description: 小顶堆，解决top n问题
 * 维护一个小顶堆，根元素为堆最小值，然后遍历数组，如果数组的数大于根元素，就与根元素交换，并重新调整小顶堆，把小顶堆中的
 * 最小值放到根元素上
 */
public class MinTopHeap {

    @Test
    public void test() {
        int[] array = {100, 11, 34, 121, 88, 45, 65, 1, 199, 200};
        topN(array, 4);
        System.out.println(Arrays.toString(array));
    }

    public void makeHeap(int[] a, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjust(a, i, n);
        }
    }

    /**
     * @param a
     * @param i
     * @param n <p>Description:小根堆 </p>
     */
    private void adjust(int[] a, int i, int n) {
        int j = 2 * i + 1;
        while (j < n) {
            if (j + 1 < n && a[j] > a[j + 1]) {
                j++;
            }//选取左右孩子中较小的
            if (a[i] > a[j]) {
                swap(a, i, j);
            } else {
                break;
            }
            i = j;
            j = 2 * i + 1;
        }
    }


    public void topN(int[] a, int n) {
        makeHeap(a, n);

        for (int i = n; i < a.length; i++) {
            if (a[i] <= a[0]) {
                continue;
            } else {
                swap(a, i, 0);
                adjust(a, 0, n);
            }
        }
    }

    /**
     * @param a
     * @param i
     * @param j <p>Description: </p>
     */
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;

    }

    /**
     * @param a
     * @param n <p>Description:堆排序 （这部分可忽略）</p>
     */
    public void HeapSort(int[] a, int n) {

        makeHeap(a, n);
        for (int i = n - 1; i >= 0; i--) {
            swap(a, 0, i);
            adjust(a, 0, i);
        }
    }
}