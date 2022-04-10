package com.example.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: luozijian
 * @date: 2021/12/9 15:02:01
 * @description: 实现一个线程池，当线程数=corePoolSize时，把新任务先放入queue中，queue满了再new thread
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

//    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private volatile int corePoolSize;

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
//
//    public void execute(Runnable command) {
//        if (command == null)
//            throw new NullPointerException();
//
//        int c = ctl.get();
//        if (workerCountOf(c) < corePoolSize) {
//            if (super.addWorker(command, true))
//                return;
//            c = ctl.get();
//        }
//        if (isRunning(c) && workQueue.offer(command)) {
//            int recheck = ctl.get();
//            if (! isRunning(recheck) && remove(command))
//                reject(command);
//            else if (workerCountOf(recheck) == 0)
//                addWorker(null, false);
//        }
//        else if (!addWorker(command, false))
//            reject(command);
//    }
}