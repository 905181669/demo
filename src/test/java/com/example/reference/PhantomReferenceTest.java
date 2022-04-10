package com.example.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

/**
 * @author: luozijian
 * @date: 4/28/21 11:41:51
 * @description:
 *
 * 当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，
 * 把这个虚引用加入到与之关联的引用队列中。程序可以通过判断引用队列中是否已经加入了虚引用，
 * 来了解被引用的对象是否将要被垃圾回收。程序如果发现某个虚引用已经被加入到引用队列，
 * 那么就可以在所引用的对象的内存被回收之前采取必要的行动。
 *
 * 1.软引用：如果内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存
 * 2.弱引用：只要发生gc就会回收，可以和ReferenceQueue，但是虚引用必须要和ReferenceQueue配合使用
 *
 * Reference.java pending属性作用：当对象除了被reference引用之外没有其它强引用了，
 * jvm的垃圾回收器就会将指向需要回收的对象的Reference都放入到这个队列里面，此时ReferenceHandler线程将
 * 把该引用添加到ReferenceQueue队列，其他业务线程就可以从这个ReferenceQueue队列中取出数据进行内存释放等操作了
 *
 * 这里一定要理解pending队列，什么样的Reference对象会进入这个队列：
 * 进入这个队列的Reference对象需要满足两个条件：
 * 1.    Reference所引用的对象已经不存在其它强引用；
 * 2.    Reference对象在创建的时候，指定了ReferenceQueue
 *
 */
public class PhantomReferenceTest {

    public static boolean isRun = true;

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {
        String abc = new String("abc");
        String test = new String("test");

        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();

        Thread t = new Thread() {
            public void run() {
                while (isRun) {
                    //poll方法不会被阻塞
                    Object obj = referenceQueue.poll();
                    if (obj != null) {
                        try {
                            Field rereferent = Reference.class
                                    .getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(obj);
                            System.out.println("垃圾回收器将回收对象："
                                    + result.getClass() + "@"
                                    + result.hashCode() + "\t"
                                    + (String) result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };


        t.start();

        PhantomReference<String> abcRef = new PhantomReference<>(abc, referenceQueue);
        PhantomReference<String> testRef = new PhantomReference<>(test, referenceQueue);

        /**
         * 清除强引用，如果不清除，将不会回收abc，也不会将abc加入ReferenceQueue
         * 工作流程：在将要回收abc前把abc加到ReferenceQueue中
          */
        abc = null;
        test = null;

        Thread.currentThread().sleep(2000);

        /**
         * gc触发jvm把abcRef和testRef添加到pending列表，然后Reference Handler线程把这两个引用
         * enqueue到这个referenceQueue
         */
        System.gc();
//        Thread.currentThread().sleep(2000);
//        isRun = false;

        Thread.sleep(100000000);
    }
}