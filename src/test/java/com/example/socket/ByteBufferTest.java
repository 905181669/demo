package com.example.socket;

import java.nio.ByteBuffer;

/**
 * @author: luozijian
 * @date: 4/23/21 17:10:10
 * @description:
 */
public class ByteBufferTest {

    public static void main(String[] args) throws Exception{
//        ByteBuffer readBuff = ByteBuffer.allocate(5);
        ByteBuffer readBuff = ByteBuffer.allocateDirect(1024);

        readBuff.put("123".getBytes());

        readBuff.flip();

        while (readBuff.hasRemaining()){
            System.out.println(readBuff.get());
        }

        readBuff = null;
        System.gc();

        Thread.sleep(100000);

//        System.out.println(Runtime.getRuntime().maxMemory());
    }
}