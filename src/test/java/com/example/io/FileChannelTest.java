package com.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: luozijian
 * @date: 4/27/21 17:33:57
 * @description:
 */
public class FileChannelTest {

    public static void main(String[] args) throws Exception{
//        FileInputStream fis = new FileInputStream("/Users/luozijian/Downloads/to_generatorConfig.xml");

        FileInputStream fis = new FileInputStream("/Users/luozijian/Downloads/mygitsource/zookeeper/data/version-2/log.d00000000d");
        FileChannel fileChannel = fis.getChannel();

        ByteBuffer readBuff = ByteBuffer.allocate(1024);

        /**
         * 从fileChannel不断读到readBuff(一开始为写模式)，读完后flip将readBuf切为读模式，读完再flip切为写模式
         */
        int read;
        while ((read = fileChannel.read(readBuff)) != -1){

            readBuff.flip();

            while (readBuff.hasRemaining()){
                System.out.println(readBuff.get());
            }
            readBuff.clear();
        }


        FileOutputStream os = new FileOutputStream(new File("/Users/luozijian/Downloads/mygitsource/zookeeper/data/version-2/log.d00000000d"));

        //get时如果为null会new一个FileChannel
        FileChannel fileChannel1 = os.getChannel();

        fis.close();
        os.close();

    }
}