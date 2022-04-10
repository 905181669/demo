package com.example.concurrent;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author: luozijian
 * @date: 6/20/21 12:09:04
 * @description:
 */
public class RateLimiterTest {

    /**
     * Guava框架支持突发流量，但是在突发流量之后再次请求时，会被限速，也就是说:在突发流量之后，
     * 再次请求时，会弥补处理突发请求所花费的时间。所以，我们的突发示例程序中，在一次从桶中获取50 个令牌后，
     * 再次从桶中获取令牌，则会花费10秒左右的时间。
     * ateLimiter在没有足够的令牌发放时，采用的是滞后的方式进行处理，也就是前一个请求获取令牌所需要等待的时间
     * 由下一次请求来承受和弥补，也就是代替前一个请求进行等待
     * @param args
     */
    public static void main(String[] args) {
        RateLimiter limiter = RateLimiter.create(5);
        System.out.println(limiter.acquire(50)); //不需要等待
        System.out.println(limiter.acquire(5)); //等10秒
        System.out.println(limiter.acquire(50)); //不需要等待
        System.out.println(limiter.acquire(5)); //等10秒

    }

    /**
     * 单例对象枚举类写法
     */
    private enum Singleton{
        INSTANCE;

        private Object singleton;
        Singleton(){
            singleton = new Object();
        }
        public Object getInstance(){
            return singleton;
        }
    }

}