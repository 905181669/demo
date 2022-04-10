package com.example.timer;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 2/26/21 15:58:37
 * @description:
 */
public class SchedulePoolTest {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(()->{
            System.out.println(Thread.currentThread() + new Date().toString());
        },1,1, TimeUnit.SECONDS);


    }
}
