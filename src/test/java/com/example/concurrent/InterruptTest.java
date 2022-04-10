package com.example.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: luozijian
 * @date: 2022/2/28
 * @description:
 */
public class InterruptTest {

    public static void main(String[] args) throws Exception{

        Thread t1 = new Thread(()->{
            try{
                System.out.println("进入线程t1");

                int index = 0;

                for(int i = 0; i< Integer.MAX_VALUE; i++){
                    index++;
                }

                System.out.println("线程t1开始sleep");
                Thread.sleep(10000);
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("finish线程t1");
            }
        });


        t1.start();
        System.out.println("执行t1.interrupt()");
        t1.interrupt(); // 线程在睡眠状态时，调用interrupt方法会报InterruptedException异常
        System.out.println(t1.isInterrupted());

        t1.join();

    }
}