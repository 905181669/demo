package com.example.socket;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.List;

/**
 * @author: luozijian
 * @date: 1/7/21 19:58:28
 * @description:
 */
public class FileTest {

    public static void main(String[] args) {

        //1m=1048576byte
        //获取当前堆的大小 byte 单位
        long heapSize = Runtime.getRuntime().totalMemory();
        System.out.println(heapSize); //1572864

        //获取堆的最大大小byte单位
        //超过将抛出 OutOfMemoryException
        long heapMaxSize = Runtime.getRuntime().maxMemory();
        System.out.println(heapMaxSize);

        //获取当前空闲的内存容量byte单位
        long heapFreeSize = Runtime.getRuntime().freeMemory();
        System.out.println("第一次剩下：" + heapFreeSize);


        //-XX:TLABSize=1k，1024时，TLAB装不下了，只能在堆上分配内存, 差应该大于0
        byte[] bytes = new byte[1024];

        long heapFreeSize2 = Runtime.getRuntime().freeMemory();
        System.out.println("第二次剩下：" + heapFreeSize2);

        System.out.println("差：" + (heapFreeSize-heapFreeSize2));

    }

    @Test
    public void testFile(){
        File file = new File("/Users/luozijian/Downloads/aaa.js");

        System.out.println(file.canRead());

        //获得本类的所在位置
        URL base = this.getClass().getResource("");

        System.out.println(base);

    }



    @Test
    public void testFile1() throws Exception{
        File file = new File("/Users/luozijian/Desktop/cept_phone.txt");

        FileInputStream is = new FileInputStream(file);
        byte[] bytes = new byte[is.available()];

        int count = is.read(bytes);

        String str = new String(bytes);

        String[] list = str.split("\r\n");

        for(String phone : list){
            System.out.println(phone);
        }


    }

    @Test
    public void testByte() throws Exception{

        char a = '\\';
        System.out.println(a);

        char b = '@';
        System.out.println(b);

        byte[] bytes = new byte[10];
        bytes[0] = 92;
        bytes[1] = 64;
        bytes[2] = 97;

        String res= new String(bytes, "UTF-8");
        System.out.println(res);

    }



}
