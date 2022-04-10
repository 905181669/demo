package com.example.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: luozijian
 * @date: 7/11/21 19:59:32
 * @description:
 */
public class WaitNotifyCase {

    public static void main(String[] args) {
        // final Object lock = new Object();
        final Object lock = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("thread A get lock");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("thread A do wait method");
                        lock.wait();

                        System.out.println("需要线程B释放锁，我才能继续走下去");
                        System.out.println("wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread B is waiting to get lock");
                synchronized (lock) {
                    System.out.println("thread B get lock");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread B do notify method");

                    //notify后，线程A并不是马上执行，需要线程B走出临界区后，线程A才能获取到锁，从wait()那里继续执行
                    lock.notify();
                    System.out.println("thread A will be alive in 10 seconds");
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    System.out.println("thread B aready notify thread A");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("特么我睡醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}