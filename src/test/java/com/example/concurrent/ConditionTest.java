package com.example.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: luozijian
 * @date: 6/19/21 09:58:59
 * @description:
 */
public class ConditionTest {

    public static void main(String[] args) throws Exception{
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        Thread t1 = new Thread(()->{
            lock.lock();
            try{
                System.out.println("t1进入await");
                condition1.await(); //先释放锁再park
                System.out.println("t1结束await");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        t1.start();

        TimeUnit.SECONDS.sleep(2);

        //多次启动会报错，原因：threadStatus != 0
//        t1.start();

        lock.lock();
        System.out.println("main线程唤醒t1");

        condition1.signal();

        //唤不醒t1
//        condition2.signal();


        lock.unlock();

    }
}