package com.example.thread;

/**
 * @author: luozijian
 * @date: 6/16/21 21:23:29
 * @description:
 * 对final变量的写，happen-before于final域对象的读，happen-before与后续对final变量的读
 * 但是这里无法复现对象new一半时的场景
 */
public class FinalTest {

    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(()->{
           Example.write();
        });

        Thread t2 = new Thread(()->{
            Example.read();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

}

class Example{

    private int i;
    private int j;
    private static Example obj;

    public Example(){
        i = 1;
        j = 2;
    }

    public static void write(){
        obj = new Example();
    }

    public static void read(){
        if(obj != null){
            int a = obj.i;
            int b = obj.j;
            System.out.println(a); //如果new到一半，a应该等于0
        }
    }
}