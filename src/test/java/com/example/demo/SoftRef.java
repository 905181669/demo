package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.ref.SoftReference;

/**
 * @author: luozijian
 * @date: 2020-06-14 11:48:58
 * @description:
 */
public class SoftRef {

    @AllArgsConstructor
    @Data
    public static class User{
        private int id;

        private String name;
    }

    public static void main(String[] args) {
        User user = new User(1, "tom");
        SoftReference<User> userSoftReference = new SoftReference<>(user);
        user = null;

        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("After gc;");
        System.out.println(userSoftReference.get());


        byte[] b = new byte[1024*924*7];
        System.gc();
        System.out.println(userSoftReference.get());

    }

}
