package com.example.callback;

/**
 * @author: luozijian
 * @date: 9/1/21 10:02:56
 * @description:
 */
public class MyTask implements Runnable{

    private Runnable task;
    private MyPromise promise;

    public MyTask(Runnable task, MyPromise promise){
        this.promise = promise;
        this.task = task;
    }
    @Override
    public void run() {
        task.run();
        promise.setValue(true);
    }
}