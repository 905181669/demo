package com.example.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 6/19/21 11:44:40
 * @description:
 *
 * 利用condition设置一个栏栅，参数paties为栏栅数，只有当paties个线程调用cyclicBarrier.await()时(count--)，
 * 当count == 0，每个线程继续往下走
 */
@Slf4j
public class CyclicBarrierExample {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1; i++){
            final int threadNum = i;
//            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                } });
        }
        executorService.shutdown();
    }
    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();
        log.info("{} continue", threadNum);
    }
}