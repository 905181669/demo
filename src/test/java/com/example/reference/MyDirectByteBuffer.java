package com.example.reference;


import sun.misc.Cleaner;

/**
 * @author: luozijian
 * @date: 4/28/21 10:59:50
 * @description:
 */
public class MyDirectByteBuffer {

    private final Cleaner cleaner;


    public MyDirectByteBuffer(){
        /**
         * 将this对象注册到一个Cleaner虚引用，当没有指向MyDirectByteBuffer的强引用时，
         * 在gc前，jvm会把这个虚引用添加到引用队列，
         * 然后跑着Reference类的tryHandlePending的线程(daemon)会
         * 执行Clean中trunk任务
         */
        cleaner = Cleaner.create(this, new MyDeallocator());
    }


    /**
     * 首先，DirectBuffer buf 如果已经没有强引用了，
     * 那么JVM就会发现只有一个Cleaner还在引用着这个buf，
     * 那么就会把与此buf相关的那个cleaner放到一个名为pending的链表里。
     * 这个链表是通过Reference.discovered域连接在一起的。
     *
     * 接着，Reference Handler这个线程会不断地从这个pending链表上取出新的对象来。
     * 它可能是WeakReference，也可能是SoftReference，当然也可能是PhantomReference和Cleaner。
     *
     * 最后，如果是Cleaner，那就直接调用Cleaner的clean方法，然后就结束了。
     * 其他的情况下，要交给这个对象所关联的queue，以便于后续的处理。
     */
    private static class MyDeallocator
            implements Runnable{
        @Override
        public void run() {
            // unsafe.freeMemory(address);
            System.out.println(Thread.currentThread().getName()
                    + "-当MyDirectByteBuffer被gc时，执行这里的方法");
        }
    }
}