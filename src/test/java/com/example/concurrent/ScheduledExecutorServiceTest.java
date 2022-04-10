package com.example.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 6/19/21 08:26:15
 * @description:
 *
 * 执行流程：Worker(包装了Thread)-->run()-->runWorker()-->getTask()-->task.run()-->
 */
public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        //ScheduledThreadPoolExecutor
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);


        Runnable task  = new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
            }
        };
        executorService.scheduleAtFixedRate(task, 0, 30, TimeUnit.SECONDS);

    }
}