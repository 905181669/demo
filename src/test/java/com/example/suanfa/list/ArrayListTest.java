package com.example.suanfa.list;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: luozijian
 * @date: 7/5/21 15:14:12
 * @description:
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(0, "a");
        System.out.println(list);

    }

    @Test
    public void test_copy_remove() {
        int[] oldArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int index = 2;
        int numMoved = 10 - index - 1;
        System.arraycopy(oldArr, index + 1, oldArr, index, numMoved);
        System.out.println("数组元素:" + JSON.toJSONString(oldArr));
    }

    @Test
    public void test_LinkedList_addFirst() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            list.addFirst(i);
        }
        System.out.println("耗时:" + (System.currentTimeMillis() - startTime));

        list.remove(1);


    }



}