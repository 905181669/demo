package com.example.io;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author: luozijian
 * @date: 4/25/21 14:07:20
 * @description:
 */
public class ZeroCopyTest {

    public static void main(String[] args) throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("/Users/luozijian/Downloads/generatorConfig.xml", "rw");

        FileChannel  fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("/Users/luozijian/Downloads/to_generatorConfig.xml", "rw");

        FileChannel toChannel = toFile.getChannel();

        long position = 0;

        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);

    }
}