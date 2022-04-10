package com.example.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: luozijian
 * @date: 9/20/21 10:11:53
 * @description:
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws Exception{

        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try{
                lock.lock();

                //执行
                System.out.println("t1获取到锁");
                Thread.sleep(10000);

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("t1释放锁");
            }
        });

        Thread t2 = new Thread(()->{
            try{
                lock.lockInterruptibly();

                //执行
                System.out.println("t2获取到锁");
            }catch (Exception e){
                e.printStackTrace();

            }finally {
                lock.unlock();
                System.out.println("t2释放锁");
            }
        });

        t1.setName("t1");
        t2.setName("t2");

        t1.start();

        Thread.sleep(100);

        t2.start();

        Thread.sleep(5000);

//        t2.interrupt(); // == Thread.interrupted()

        System.in.read();
    }
}