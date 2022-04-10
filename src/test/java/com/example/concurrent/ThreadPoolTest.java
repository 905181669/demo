package com.example.concurrent;

/**
 * @author: luozijian
 * @date: 6/18/21 15:14:01
 * @description:
 */
public class ThreadPoolTest {

    private static final int COUNT_BITS = Integer.SIZE - 3; //表示线程池中的最大线程数量
    //将数字1的二进制值向右移29位，再减去1
    private static final int CAPACITY =(1 << COUNT_BITS)-1;

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE); //1 << 31 = 2147483647
        System.out.println(COUNT_BITS);
        System.out.println(CAPACITY); // 1 << 29

        System.out.println((int)Math.pow(2, 31)); //2的31次方

    }
}