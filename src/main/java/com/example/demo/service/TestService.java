package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: luozijian
 * @date: 2022/4/17
 * @description:
 */

@Service
public class TestService {

    /**
     * Spring的@Async线程池的配置在：TaskExecutionAutoConfiguration类
     * queueCapacity为Integer.MAX_VALUE
     * coreSize为cpu数*2
     * maxPoolSize为Integer.MAX_VALUE
     * keepalive为默认60s
     *
     */
    @Async("asyncTaskExecutor")
    public void test() {
        System.out.println("当前线程: " + Thread.currentThread().getName());
        try{
            Thread.sleep(10000);
        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}