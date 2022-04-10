package com.example.thread;

import java.util.concurrent.*;

/**
 * @author: luozijian
 * @date: 2022/1/15
 * @description:
 */
public class ExecutorTest {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(0, 0,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1));

        executorService.execute(()->{
            System.out.println("111");
        });

    }
}