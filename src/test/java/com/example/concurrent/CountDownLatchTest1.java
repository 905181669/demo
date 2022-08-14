package com.example.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author: luozijian
 * @date: 2022/6/27
 * @description:
 */
public class CountDownLatchTest1 {

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        final Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();


        new Thread(()->{

            try{
                latch.await();
            }catch (Exception e) {
                e.printStackTrace();
            }

            synchronized (o) {
                for(char c : aI) {
                    System.out.print(c);
                    try{
                        o.notify();
                        o.wait();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();

        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }


        new Thread(()->{
            synchronized (o) {
                for(char c : aC) {
                    System.out.print(c);

                    //countDown可以无限执行
                    latch.countDown();
                    try{
                        o.notify();
                        o.wait();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
    }
}