package com.example.reference;

/**
 * @author: luozijian
 * @date: 1/9/21 10:43:57
 * @description:
 */
public class CharTest {

    /**
     * 一个线程只有一个ThreadLocalMap，ThreadLocalMap里key是threadLocal，value是值
     *
     * ThreadLocalMap最后的结果是：
     * {threadLocal1:value1, threadLocal2:value2}
     */
    static ThreadLocal tl1 = new ThreadLocal();
    static ThreadLocal tl2 = new ThreadLocal();
    public static void main(String[] args) throws Exception{

        Thread t = new Thread(()->{
            tl1.set("1");
            System.out.println(tl1.get());
            tl2.set("2");
            System.out.println(tl2.get());

            tl1.remove();


        });


        t.setName("我是t");
        t.start();

        t.join();


    }
}
