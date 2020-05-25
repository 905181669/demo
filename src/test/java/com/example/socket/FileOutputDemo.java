package com.example.socket;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: luozijian
 * @date: 2020-04-21 21:18:20
 * @description:
 */
public class FileOutputDemo {

    public static void main(String[] args) throws Exception{
        byte[] content = "测试".getBytes();

        FileOutputStream fs = new FileOutputStream("/Users/luozijian/test.txt");
        FileChannel fileChannel = fs.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        for(int i = 0 ; i < content.length ; i++){
            byteBuffer.put(content[i]);
        }

        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        fs.close();


    }
}
