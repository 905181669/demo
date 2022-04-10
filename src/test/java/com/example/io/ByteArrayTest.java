package com.example.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author: luozijian
 * @date: 2/1/21 22:05
 * @description:
 */
public class ByteArrayTest {

    public static void main(String[] args) throws Exception{
//        FileOutputStream fos = new FileOutputStream("./byteArray");
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//
//        byte[] bytes = {97, 98, 99};
//        os.write(bytes);
//
//        for(int b : os.toByteArray()){
//            System.out.println(b);
//        }
//
//        os.writeTo(fos);



        FileInputStream fis = new FileInputStream("./byteArray");

        byte[] bytes1 = new byte[10];
        fis.read(bytes1);

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes1);
        int re;
        while((re = bis.read()) != -1){
            System.out.println(re);
        }



    }
}
