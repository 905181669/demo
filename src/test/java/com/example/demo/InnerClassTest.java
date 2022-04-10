package com.example.demo;

/**
 * @author: luozijian
 * @date: 10/13/21 15:48:25
 * @description:
 */
public class InnerClassTest {

    private String a;

    private static String b = "b";

    public InnerClassTest(String a) {
        this.a = a;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            System.out.println(a);
            System.out.println(b);
        }
    };

    private static Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
//            System.out.println(a);
            System.out.println(b);
        }
    };

    public static void main(String[] args) {
        new InnerClassTest("a").runnable1.run();
        InnerClassTest.runnable1.run();
    }
}