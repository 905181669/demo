package com.example.demo;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2020-07-04 15:02:33
 * @description:
 */
public class GcTest {

    public static void main(String[] args) {

        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = null;
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = null;
//
//        byte[] array2 = new byte[128 * 1024];
//
//        byte[] array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = null;
//
//        byte[] array4 = new byte[2 * 1024 * 1024];


    }

    @Test
    public void testMemory(){
        //初始堆大小为系统内存1/64，堆内存最大为系统内存1/4。
        long initialHeapSize = Runtime.getRuntime().totalMemory();
        long maxHeapSize = Runtime.getRuntime().maxMemory();
        System.out.println("-Xms = " + initialHeapSize / 1024 + "kb or " + initialHeapSize / 1024 / 1024 + "mb");
        System.out.println("-Xmx = " + maxHeapSize / 1024 + "kb or " + maxHeapSize / 1024 / 1024 + "mb");

    }
}
