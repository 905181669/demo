package com.example.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: luozijian
 * @date: 1/23/21 08:19:50
 * @description:
 */
public class MyReentrantLockTest {

    public static void main(String[] args) {
        MyReentrantLock lock = new MyReentrantLock();

        Thread t0 = new Thread(()->{

            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "测试自实现的lock");
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        });

        Thread t1 = new Thread(()->{

            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "测试自实现的lock");
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        });


        t0.start();
        t1.start();

    }
}
