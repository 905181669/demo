package com.example.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: luozijian
 * @date: 1/21/21 18:08:34
 * @description:
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws Exception{
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(()->{
            try{
                lock.lock();
                System.out.println("t1进入锁");
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(()->{
            try{
                //lock()会阻塞等待
                lock.lock();
                System.out.println("t2进入锁");
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        Thread t3 = new Thread(()->{
            try{
                //lock()会阻塞等待
                lock.lock();
                System.out.println("t2进入锁");
                Thread.sleep(10000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });

        t1.start();

        Thread.sleep(1000);

        t2.start();

        t3.start();


    }

}
