package com.example.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: luozijian
 * @date: 7/12/21 08:34:09
 * @description:
 */
public class SpuriousWakeUp1 {

    public static void main(String[] args) throws Exception{

        Object lock = new Object();
        AtomicInteger count = new AtomicInteger();

        Thread t1 = new Thread(()->{

            synchronized (lock){

                if(count.get() >= 0){

                    try{
                        System.out.println("线程1开始wait");
                        lock.wait();
                        System.out.println("线程1结束wait");

                        Thread.sleep(10000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else {
                    count.incrementAndGet();
                }
            }

        });


        Thread t2 = new Thread(()->{

            synchronized (lock){

                if(count.get() >= 0){

                    try{
                        System.out.println("线程2开始wait");
                        lock.wait();
                        System.out.println("线程2结束wait");
                        Thread.sleep(10000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else {
                    count.incrementAndGet();
                }
            }

        });


        Thread t3 = new Thread(()->{

            synchronized (lock){

                System.out.println("线程3调用notifyAll");
                lock.notifyAll();
            }

        });


        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(3);

        t3.start();




    }

}