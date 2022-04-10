package com.example.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Callable;

/**
 * @author: luozijian
 * @date: 4/27/21 14:41:33
 * @description:
 */
public class BufferedInputStreamTest {

    public static void main(String[] args) throws Exception{

        File file = new File("/Users/luozijian/Downloads/arthas-boot.jar");
        InputStream is = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(is);

//        is.available();
        byte[] b = new byte[1024];

        /**
         * FileInputStream.read(byte[] b)，不断从流中读取字节到b数组中，自己维护一个读位置，
         * 不可逆，每读一个字节就自动+1
         * InputStream的read(byte[] b)默认是一个字节一个字节读，FileInputStream重写了read(byte[] b)，可以
         * 一串一串byte[]地读
         */
        int read;
        while((read = bis.read(b)) != -1){
            System.out.println(new String(b));
        }

        //只读一个字节
//        System.out.println(bis.read());




    }
}