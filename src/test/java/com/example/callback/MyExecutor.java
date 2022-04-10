package com.example.callback;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 9/1/21 09:57:19
 * @description:
 */
public class MyExecutor {

    static Executor executor = Executors.newSingleThreadExecutor();

    public static MyPromise execute(Runnable task){
        MyPromise promise = new MyPromise();

        executor.execute(new MyTask(task, promise));

        return promise;
    }
}