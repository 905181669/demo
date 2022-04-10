package com.example.concurrent;

import sun.misc.Unsafe;

import java.time.LocalDateTime;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: luozijian
 * @date: 1/24/21 08:32:06
 * @description:
 */
public class ParkTest {


    public static void main(String[] args) throws Exception {

        Thread t0 = new Thread(()->{

            try{
                Thread.sleep(2000);
            }catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(LocalDateTime.now() + " 挂起t0");
            LockSupport.park(Thread.currentThread());
            System.out.println(LocalDateTime.now() + " 恢复t0");

        });

        t0.start();

//        LockSupport.unpark(t0); //在调用park()之前调用了unpark或者interrupt则park直接返回，不会挂起
        Thread.sleep(4000);
        LockSupport.unpark(t0);

        t0.join();

    }
}
