package com.example.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: luozijian
 * @date: 2020-12-28 20:26:47
 * @description:
 */
public class MyFutureTaskTest1 {

    public static void main(String[] args) throws Exception{

        Executor executor = Executors.newSingleThreadExecutor();
        MyFutureTask future = new MyFutureTask(()->{

            Thread.sleep(10000000);
            return "测试";
        }, Thread.currentThread());

        executor.execute(future);

//        System.out.println(future.get());

        System.out.println(future.get(5, TimeUnit.SECONDS));
    }
}


class MyFutureTask<V> implements RunnableFuture<V> {

    private Callable<V> callable;

    private Thread parent;

    private Object outcome; // non-volatile, protected by state reads/writes

    private volatile int state;
    private static final int NEW  = 0;
    private static final int NORMAL = 2;


    public MyFutureTask(Callable<V> callable, Thread parent) {
        this.callable = callable;
        this.state = NEW;
        this.parent = parent;
    }

    @Override
    public void run() {
        try{
            Callable<V> c = callable;
            outcome = c.call();
            state = NORMAL;
            LockSupport.unpark(parent);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        for(;;){
            if(state == NORMAL){
                return (V)outcome;
            }else {
                LockSupport.park(this);
                break;
            }
        }
        return (V)outcome;
    }


    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        long nanos = unit.toNanos(timeout);
        final long deadline =  System.nanoTime() + nanos ;
        for(;;){
            if(state == NORMAL){
                return (V)outcome;
            }else {
                long diff = deadline - System.nanoTime();
                if(diff < 0L){
                    break;
                }
                LockSupport.parkNanos(this, nanos);

            }
        }

        if(state == NEW){
            throw new TimeoutException("超时了");
        }
        return (V)outcome;
    }
}