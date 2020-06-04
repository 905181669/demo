package com.example.structure;

import com.google.common.collect.Maps;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: luozijian
 * @date: 2020-05-31 10:24:30
 * @description:
 */
public class MapTest {

    public static void main(String[] args)throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);

        Unsafe unsafe = (Unsafe) f.get(null);
        Object obj = new Object();
        long markword = unsafe.getInt(obj, 1L);

        System.out.println(markword);


    }

}
