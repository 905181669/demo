package com.example.demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: luozijian
 * @date: 2020-10-03 10:20:25
 * @description:
 */
public class LockTest {


    public static void main(String[] args) throws Exception{

        ReentrantLock lock = new ReentrantLock();
        Thread threadA = new Thread(()->{

            try{
                lock.lock();

                try{
                    while(true){
//                        System.out.println(1);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        });


        Thread threadB = new Thread(()->{

            try{
                lock.lock();

                try{
                    while(true){
//                        System.out.println(1);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        });


        threadA.start();
        Thread.sleep(100);

        threadB.start();

        Thread.sleep(1000);
        threadB.interrupt();

        Thread.sleep(100);
    }


}
