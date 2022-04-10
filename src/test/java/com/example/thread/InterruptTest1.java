package com.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 6/19/21 15:34:12
 * @description:
 */
public class InterruptTest1 {

    public static void main(String[] args) throws Exception{

        Thread t1 = new Thread(()->{

            /**
             * 如果要正确中断一个运行中的线程，需要在逻辑中设置检查中断位代码，
             * 不然其他线程调用interrupt()没用
             */
            while(!Thread.currentThread().isInterrupted()){

            }

            System.out.println("线程1退出");

        });
        t1.start();

        TimeUnit.SECONDS.sleep(2);

        t1.interrupt();


        System.out.println("主线程over");

    }


    /**
     *
     * AQS中，线程在park期间，如果被别的线程设置了中断位，当unpark时，取消中断位并返回true，
     * 然后自己再设置一次中断位标志
     * public final void acquire(int arg) {
     *         if (!tryAcquire(arg) &&
     *             acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
     *             selfInterrupt();
     *     }
     */

}