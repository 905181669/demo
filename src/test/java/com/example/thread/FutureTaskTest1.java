package com.example.thread;

import java.util.concurrent.*;

/**
 * @author: luozijian
 * @date: 6/7/21 20:32:07
 * @description:
 *
 * 线程池-线程：
 * ExecutorService.submit()-->AbstractExecutorService.submit()-->原始task封装为FutureTask-->
 * ExecutorService.execute(FutureTask)-->FutureTask.run()-->原始task.run()-->
 * 设置成功并通知唤醒调用futureTask.get()的线程(遍历WaitNode，拿到WaitNode关联的线程并一一唤醒)
 *
 * 主线程：调用futureTask.get()-->创建FutureTask.WaitNode(Thread thread = Thread.currentThread)
 * -->UNSAFE.park()
 *
 * 分析：FutureTask相当于一个媒介，沟通线程池线程和主线程
 */
public class FutureTaskTest1 {


    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<String> futureTask = executor.submit(()->{
            Thread.sleep(3000);
            return "hello";
        });

        String hello = futureTask.get();

//        System.out.println(hello);
    }
}