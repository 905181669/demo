package com.example.optimize;

/**
 * @author: luozijian
 * @date: 1/10/21 21:06:23
 * @description:
 */
public class Test1 {

    public static void main(String[] args) throws Exception{
        long start = System.currentTimeMillis();
        for(int i=0; i < 2000; i++){
            Thread.sleep(2);
        }
        long end = System.currentTimeMillis();
        System.out.println("Millis elapsed: " + (end-start)/4000.0);

    }
}
