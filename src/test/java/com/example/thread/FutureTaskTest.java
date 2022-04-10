package com.example.thread;

import sun.misc.Contended;

import java.util.concurrent.*;

/**
 * @author: luozijian
 * @date: 1/21/21 15:33:43
 * @description:
 */
public class FutureTaskTest {

    /**
     * 关键点在于WaitNode，当get()进入阻塞状态，把当前阻塞的线程构造一个WaitNode链表，当异步线程执行完任务后，
     * 调用set()返回，
     * 然后遍历循环WaitNode链表进行unpark操作
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        AbstractExecutorService executor = new ThreadPoolExecutor(1, 2,
                10L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(5));


        FutureTask futureTask = new FutureTask(()->{

            Thread.sleep(1000);
            System.out.println("执行的线程是：" + Thread.currentThread().getName());
            return "测试";
        });


        Runnable task = new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    System.out.println("执行的线程是：" + Thread.currentThread().getName());
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        };


//        executor.shutdown();
        for(int i =0; i < 11; i++){
            executor.execute(task);
        }





    }

    /**
     * /**
     *      * Executes the given task sometime in the future.  The task
     *      * may execute in a new thread or in an existing pooled thread.
     *      *
     *      * If the task cannot be submitted for execution, either because this
     *      * executor has been shutdown or because its capacity has been reached,
     *      * the task is handled by the current {@code RejectedExecutionHandler}.
     *      *
     *      * @param command the task to execute
     *      * @throws RejectedExecutionException at discretion of
     *      *         {@code RejectedExecutionHandler}, if the task
     *      *         cannot be accepted for execution
     *      * @throws NullPointerException if {@code command} is null
     *
     *

    public void execute(Runnable command) {
     *if (command == null)
     *throw new NullPointerException();
     *         /*
         *          * Proceed in 3 steps:
         *          *
         *          * 1. If fewer than corePoolSize threads are running, try to
         *          * start a new thread with the given command as its first
         *          * task.  The call to addWorker atomically checks runState and
         *          * workerCount, and so prevents false alarms that would add
         *          * threads when it shouldn't, by returning false.
         *          *
         *      1.如果工作线程小于核心线程池数量，尝试新建一个工作线程执行任务addWorker。
         *          addWorker将会自动检查线程池状态和工作线程数，以防在添加工作线程的过程中，
         *      线程池被关闭。
         *          * 2. If a task can be successfully queued, then we still need
         *          * to double-check whether we should have added a thread
         *          * (because existing ones died since last checking) or that
         *          * the pool shut down since entry into this method. So we
         *          * recheck state and if necessary roll back the enqueuing if
         *          * stopped, or start a new thread if there are none.
         *          *
         *      2.如果创建工作线程执行任务失败，则任务入队列，如果入队列成功，
         *      我们仍需要二次检查线程池状态，以防在入队列的过程中，线程池关闭。
         *      如果线程池关闭，则回滚任务。
         *          * 3. If we cannot queue task, then we try to add a new
         *          * thread.  If it fails, we know we are shut down or saturated
         *          * and so reject the task.
         *      如果任务入队列失败，则尝试创建一个工作线程执行任务
         *
     *int c = ctl.get();
     *if (workerCountOf(c) < corePoolSize) {
     *         //如果当前工作线程数小于核心线程池数量，则添加新的工作线程执行任务
     *if (addWorker(command, true))
     *return;
     *c = ctl.get();
     *}
     *     //如果当前工作线程数大于核心线程池数量，检查运行状态，如果是正在运行，则添加任务到任务队列
     *if (isRunning(c) && workQueue.offer(command)) {
     *int recheck = ctl.get();
     *         //重新检查线程池运行状态，如果线程池非处于运行状态，则移除任务
     *if (!isRunning(recheck) && remove(command))
     *reject(command);//移除成功，则进行拒绝任务处理
     *             else if (workerCountOf(recheck) == 0)
     *             //如线程池已关闭，且工作线程为0，则创建一个空闲工作线程
     *addWorker(null, false);
     *}
     *        //根据最大线程池数量，判断是否应该添加工作线程，如果当前工作线程数量小于最大线程池数量，则尝试添加
     *        //工作线程线程执行任务，如果尝试失败，则拒绝任务处理
     *         else if (!addWorker(command, false))
     *reject(command);
     *}
     **/
}


