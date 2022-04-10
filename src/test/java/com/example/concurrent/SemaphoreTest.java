package com.example.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: luozijian
 * @date: 7/21/21 09:42:03
 * @description:
 */
@Slf4j
public class SemaphoreTest {

    public static void main(String[] args) {

        Semaphore sp = new Semaphore(2);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i  = 0; i < 5; i++){
            executorService.execute(()->{

                try{
                    sp.acquire(2);
                    log.info("获取到信号量，继续执行");

                    Thread.sleep(2000);

                }catch (Exception e){
                    e.printStackTrace();

                }finally {
                    sp.release(2);
                }
            });
        }









    }
}