package com.example.thread;



/**
 * @author: luozijian
 * @date: 1/21/21 15:46:16
 * @description:
 */
public class UnsafeTest {

    public static void main(String[] args) throws Exception {
        MyAtomicInteger myAtomicInteger = new MyAtomicInteger(0);

        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];

        for(int i = 0; i < threadCount; i++){
            Thread t = new Thread(()->{

                for(int j = 0; j < 10000; j++){
                    myAtomicInteger.increaseAndGet();

                }
            });
            t.setName("myThread-" + i);
            threads[i] = t;
            t.start();
        }

        Thread.sleep(1000);
        for(Thread t : threads){
            t.join();
        }

        System.out.println(myAtomicInteger.get());

    }
}

class MyAtomicInteger{

    /**
     * happen-before
     * (3)对volatile字段的写入操作happens-before于每一个后续的同一个字段的读操作
     * 涩会难懂，简单来说就是对volatile字段的写，应该对后续其他线程或当前线程都可见
     *
     * 很奇怪，此变量即使不用volatile修饰，也能得到正确的结果
     * Google查不到原因，但从volatile语义来推测，volatile保证了可见性和禁止指令重排序，应该与这两个特性
     * 有关吧
     */
    volatile int val;



    private static final long valueOffset;


    static {
        Class<?> k = MyAtomicInteger.class;
        try{
            valueOffset = MyUnsafe.get().objectFieldOffset
                    (k.getDeclaredField("val"));
        }catch (Exception e){
            throw new Error(e);
        }
    }



    public MyAtomicInteger(int i){
        val = i;
    }

    public int increaseAndGet(){
//        return val++;
//        return MyUnsafe.get().getAndAddInt(this, valueOffset, 1) + 1;
        int threadCur;
        do{
            /**
             * 不断从主内存中拿，threadCur是本次循环获取的最新值，如果当前最新值=主内存中的值（这个值由CAS根据内存地址偏移量获取），
             * 就更新为新值并同步到主内存，
             * 这样其他线程就能看到更新后的值了
             *
             */
            threadCur = get();
        }while (!MyUnsafe.get().compareAndSwapInt(this, valueOffset, threadCur, threadCur + 1));

        /**
         * 这里加1，是为了给当前线程看的，思考了很久！
         * 因为更新主内存成功后(+1)，也要把自己工作内存中的oldExpect加1
         * 这里直接返回get()会有问题，比如当前线程执行到这里，还没return，
         * 此时其他线程又更新了val，那这里返回的就不对了
         */
        return threadCur + 1;
//        return get(); //会有问题
    }


    public int get(){
        return val;
    }


}


