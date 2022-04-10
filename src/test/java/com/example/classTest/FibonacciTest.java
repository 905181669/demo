package com.example.classTest;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author: luozijian
 * @date: 1/16/21 20:37:59
 * @description:
 */
public class FibonacciTest {

    private volatile double l;
    private int nloops;

    private double[] cache;
    private int counter;


    /**
     * -XX:+PrintCompilation
     * -XX:+PrintCommandLineFlags
     * -XX:+UseParNewGC 是parNew + serial old
     * -XX:+UseParallelGC 是parallel scavenge+ serial old
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        FibonacciTest f = new FibonacciTest(10, 40);
        f.doTest(true);
//        f.doTest(false);
//        Thread.sleep(100);
    }


    private FibonacciTest(int n, int counter){
        nloops = n;
        this.counter = counter;
        cache = new double[counter];
    }



    public void doTest(boolean isWarmup){

        long then = System.currentTimeMillis();
        for(int i = 0; i < nloops; i++){
            //值为20才jit编译
            l = fibImpl1(counter);
        }
        if(true){
            long now = System.currentTimeMillis();
            System.out.println("Elapsed time:" + (now-then));
        }
        System.out.println(l);


    }

    private double fibImpl1(int n){
        if(n < 0){
            throw new IllegalArgumentException("must be > 0");
        }

        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }

//        if(cache[n-1] > 0){
//            return cache[n-1];
//        }
        double d = fibImpl1(n - 2) + fibImpl1(n - 1);
        cache[n-1] = d;
        if(Double.isInfinite(d)){
            throw new ArithmeticException("overflow");
        }
        return d;
    }
}


