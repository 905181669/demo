package com.example.socket;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2020-03-29 14:51:46
 * @description:
 */
public class EpollTest {


    public static void main(String[] args) {
        int OP_READ = 1 << 0;
        int OP_WRITE = 1 << 2;
        int OP_CONNECT = 1 << 3;
        int OP_ACCEPT = 1 << 4;
        System.out.println(OP_READ);
        System.out.println(OP_WRITE);
        System.out.println(OP_CONNECT);
        System.out.println(OP_ACCEPT);
    }


    @Test
    public void test(){
        String a = "luozjian123@zuzuche.com";
        String b = a.substring(0, a.indexOf("@"));
        System.out.println(b);
    }



}
