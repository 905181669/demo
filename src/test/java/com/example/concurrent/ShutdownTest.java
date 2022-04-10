package com.example.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 6/18/21 13:54:14
 * @description:
 */
public class ShutdownTest {

    public static void main(String[] args) throws Exception{

        ThreadPoolExecutor executorService = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
        executorService.execute(()->{

            try{
                System.out.println("子线程开始睡眠10秒");
                TimeUnit.SECONDS.sleep(10);
//                while (true){
                /**
                 * 如果线程还在执行一个任务，即使调用shutdownNow()也不会马上停止；
                 * shutdownNow的作用是设置中断位，让线程在getTask()时抛出InterruptedException异常
                 */
//
//                }
                System.out.println("子线程睡眠结束");
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        executorService.submit(()->{
            System.out.println("hello");
        });



        TimeUnit.SECONDS.sleep(2);


        /**
         * (1)getTaskCount():线程池已执行和未执行的任务总数
         * (2)getCompletedTaskCount():已完成的任务数量
         * (3)getPoolSize():线程池当前的线程数量
         * (4)getCorePoolSize():线程池核心线程数
         * (5)getActiveCount():当前线程池中正在执行任务的线程数量
         */
        System.out.println("线程池已执行和未执行的任务总数: " + executorService.getTaskCount());
        System.out.println("已完成的任务数量: " + executorService.getCompletedTaskCount());
        System.out.println("线程池当前的线程数量: " + executorService.getPoolSize());
        System.out.println("线程池核心线程数: " + executorService.getCorePoolSize());
        System.out.println("当前线程池中正在执行任务的线程数量: " + executorService.getActiveCount());


        /**
         * shutdown()不会中断工作的线程，子线程睡眠10秒后jvm进程退出
         * 不能再接收新提交的任务，但是可以处理阻塞队列中已经保存的任务，当 线程池处于Running状态时，
         * 调用shutdown()方法会使线程池进入该状态
         */
//        executorService.shutdown();

        /**
         * 不再接收新任务
         */
//        executorService.execute(()->{
//
//        });

        /**
         * shutdownNow()
         * 不能接收新任务，也不能处理阻塞队列中已经保存的任务，会中断正在处理任务的线程，
         * 如果线程池处于Running或Shutdown状态，调用shutdownNow()方法，会使线程池进入该状态
         */

        /**
         * shutdown():关闭线程池，等待任务都执行完
         * shutdownNow():立即关闭线程池，不等待任务执行完
         */
        executorService.shutdownNow();

        System.out.println("isTerminated: " + executorService.isTerminated());
        System.out.println("isTerminating: " + executorService.isTerminating());
        System.out.println("isShutdown: " + executorService.isShutdown());

        //等待线程池中的所有任务执行结束，并设置超时时间
//        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("主线程执行完");
    }
}