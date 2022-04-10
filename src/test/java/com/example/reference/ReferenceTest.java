package com.example.reference;

/**
 * @author: luozijian
 * @date: 4/28/21 10:59:39
 * @description:
 */
public class ReferenceTest {

    /**
     * ReferenceHandler.class
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        // 模拟创建一个ByteBuffer对象
        MyDirectByteBuffer buf = new MyDirectByteBuffer();

        //把强引用去掉后，jvm是如何把Cleaner虚引用添加到pending列表？
        buf = null;

        // 手动gc
        System.gc();

        System.in.read();

    }
}