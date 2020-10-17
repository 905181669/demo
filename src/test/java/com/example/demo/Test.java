package com.example.demo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.digester.Digester;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


import java.io.File;
import java.io.ObjectOutputStream;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: luozijian
 * @date: 2020-04-21 11:21:09
 * @description:
 */
public class Test {


    private static int count = 0;
    public static void recursion(){
        count ++;
        recursion();
    }

    public static void main (String args[]){

        try{
//            recursion();

            List list = new ArrayList();

            for (int i = 0 ; i < 10; i++){
                list.add(i);
            }

        }catch(Throwable e){
            System.out.println("deep of calling: " + count);
            e.printStackTrace();
        }

    }


    @org.junit.Test
    public void test1() throws Exception{


//        Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.example.demo.Function");

//        Class.forName("com.example.demo.Function");


        ArrayBlockingQueue queue = new ArrayBlockingQueue(1);
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
//        queue.put(1);
//        queue.put(2);
//        System.out.println(queue.poll());
//        System.out.println(queue.take());

        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }


    @org.junit.Test
    public void test2(){
        List v = new ArrayList();
        for(int i = 0; i < 25; i++){
            v.add(new byte[1024*1024]);
            v.add(new Book());
//            v.add(new DogBook());
        }
    }


    @org.junit.Test
    public  void test4() throws Exception{
        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
        //将缓存数据的强引用去除
        cacheData = null;
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        //在分配一个120M的对象，看看缓存对象的回收情况
        byte[] newData = new byte[120 * 1024 * 1024];
        System.out.println("分配后" + cacheData);
        System.out.println("分配后" + cacheRef.get());
    }


    @org.junit.Test
    public void test5() throws Exception{
        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        //将缓存数据的强引用去除
        cacheData = null;
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第二次GC后" + cacheData);
        System.out.println("第二次GC后" + cacheRef.get());
    }



    @org.junit.Test
    public void test6(){

        Map map = Maps.newHashMap();
        String key = "key";
        map.put(key, key);
        key = null;
        System.out.println(map.get("key") == key);


    }

    @org.junit.Test
    public void stopTheWorldTest(){

        String a = "1";
        String b = a;
        System.out.println(a);
        a = "2";
        System.out.println(a);
        System.out.println(b);
    }


    @org.junit.Test
    public void test7(){

        int threadNum = 0;
        try {
            while (true) {
                threadNum++;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        doSomething();

                    }
                });
                thread.start();
            }
        } catch (Throwable e) {
            System.out.println("目前活动线程数量：" + threadNum);
            throw e;
        }
    }

    public void doSomething() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void test9(){
        try {
            while (true){
                Enhancer enhancer=new Enhancer();
                enhancer.setSuperclass(Test.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,objects);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }


    @org.junit.Test
    public void test10(){

        /**
         * -XX:NewSize=5242880
         * -XX:MaxNewSize=5242880
         * -XX:InitialHeapSize=10485760
         * -XX:MaxHeapSize=10485760
         * -XX:SurvivorRatio=8
         * -XX:PretenureSizeThreshold=10485760
         * -XX:+UseParNewGC
         * -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails
         * -XX:+PrintGCTimeStamps
         */


        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];

    }


    @org.junit.Test
    public void test11(){
        /**
         * -XX:NewSize=10485760 10M
         * -XX:MaxNewSize=10485760
         * -XX:InitialHeapSize=20971520 20M
         * -XX:MaxHeapSize=20971520
         * -XX:SurvivorRatio=8
         * -XX:PretenureSizeThreshold=10485760
         * -XX:+UseParNewGC
         * -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails
         * -XX:+PrintGCTimeStamps
         * -XX:+PrintCommandLineFlags
         * -XX:MaxTenuringThreshold=15
         */

//        byte[] array1 = new byte[1 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = null;
//
//        byte[] array2 = new byte[128 * 1024];
//
//        byte[] array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = null;
//
//        byte[] array4 = new byte[2 * 1024 * 1024];



    }


    @org.junit.Test
    public void test12(){

        byte[] array1 = new byte[4 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
        byte[] array4 = new byte[2 * 1024 * 1024];
        byte[] array5 = new byte[128 * 1024];

        byte[] array6 = new byte[2 * 1024 * 1024];


    }


    @org.junit.Test
    public void test13() throws Exception{

        /**
         * -XX:NewSize=104857600
         * -XX:MaxNewSize=104857600
         * -XX:InitialHeapSize=209715200
         * -XX:MaxHeapSize=209715200
         * -XX:SurvivorRatio=8
         * -XX:MaxTenuringThreshold=15
         * -XX:PretenureSizeThreshold=3145728
         * -XX:+UseParNewGC
         * -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails
         * -XX:+PrintGCTimeStamps
         */

        Thread.sleep(30000);
        while (true){
            loadData();
        }



    }

    private static void loadData() throws Exception{
        byte[] data = null;
        for(int i = 0 ; i < 50; i++){
            data = new byte[100 * 1024];

        }
        data = null;
        Thread.sleep(1000);
    }


    @org.junit.Test
    public void test14() throws Exception{

        Thread.sleep(30000);
        while (true){
            loadData14();
        }
    }

    private static void loadData14() throws Exception{
        byte[] data = null;
        for(int i = 0 ; i < 4; i++){
            data = new byte[10 * 1024 * 1024];

        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];

        byte[] data2 = new byte[10 * 1024 * 1024];

        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }



}

