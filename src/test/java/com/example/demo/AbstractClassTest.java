package com.example.demo;

/**
 * @author: luozijian
 * @date: 3/24/21 20:34:10
 * @description:
 */
public class AbstractClassTest {

    public static void main(String[] args) {
        Granfather obj = new Son();
        obj.say();
    }
}

abstract class Granfather{
    public void say(){
        System.out.println("我是爷爷");
    }
}

abstract class Father extends Granfather{

    @Override
    public void say(){
        System.out.println("我是爸爸");
    }
}

class Son extends Father{


}
