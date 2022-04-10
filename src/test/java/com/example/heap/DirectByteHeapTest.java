package com.example.heap;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.ByteBuffer;

/**
 * @author: luozijian
 * @date: 6/15/21 16:10:01
 * @description:
 */
public class DirectByteHeapTest {

    public static void main(String[] args) {

//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10);
//
//        byteBuffer.put("test".getBytes());
//
//        byteBuffer.flip();
//        while (byteBuffer.hasRemaining()){
//            System.out.println(byteBuffer.get());
//        }


        /**
         * release掉，可以复用ByteBuf
         */
        for(int i = 0 ; i < 10; i++){
            ByteBuf buf = ByteBufAllocator.DEFAULT.directBuffer(1024);
            String test = "test" + i + "";
            buf.writeBytes(test.getBytes());


            byte[] bytes = new byte[buf.readableBytes()];

            while (buf.isReadable()){
                buf.readBytes(bytes);
            }

            System.out.println(new String(bytes));
            buf.release();
        }





    }
}