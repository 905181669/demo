package com.example.demo;

/**
 * @author: luozijian
 * @date: 4/28/21 11:36:49
 * @description:
 */
public class StaticTest {

    public static void main(String[] args) {
        Son1 son1 = new Son1();
    }
}

abstract class Parent{
    static {
        System.out.println("我是父静态代码段");
    }
}

class Son1 extends Parent{

}