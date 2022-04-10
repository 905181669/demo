package com.example.suanfa.bigdata;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: luozijian
 * @date: 2022/3/18
 * @description:
 */
public class MostNumber {

    public static void main(String[] args) throws Exception{
        String fileName = "/Users/luozijian/Desktop/ip.txt";

        InputStream is = new FileInputStream(fileName);
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        String[] strings = new String(bytes).split(",");
        is.close();

        List<String>[] buckets = new ArrayList[10];
        for(int i = 0; i < strings.length; i++) {
            int index = Integer.valueOf(strings[i]) % 10;
            List<String> list = buckets[index] == null ? Lists.newArrayList() : buckets[index];
            buckets[index] = list;
            list.add(strings[i]);
        }

        Map<String, Integer>[] mapArr = new HashMap[10];
        for(int i = 0; i < 10; i++) {
            mapArr[i] = Maps.newHashMap();
        }


        for(int i = 0; i < 10; i++) {
            List<String> list = buckets[i];
            Map<String, Integer> map = mapArr[i];
            for(String num : list) {
                if(map.get(num) == null) {
                    map.put(num, 1);
                }else {
                    map.put(num, map.get(num) + 1);
                }
            }
        }

        Map<String, Integer> mostNumberMap = Maps.newHashMap();
        for(int i = 0; i < 10; i++) {
            Map<String, Integer> map = mapArr[i];
            map = sortByValue(map);
            System.out.println(map);
            mostNumberMap.put(map.keySet().stream().findFirst().get(), map.values().stream().findFirst().get());
        }


        mostNumberMap = sortByValue(mostNumberMap);
        System.out.println(mostNumberMap);





    }

    public static  <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByValue()
                        .reversed()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }


    @Test
    public void createFile() throws Exception{
        String fileName = "/Users/luozijian/Desktop/ip.txt";
        OutputStream os = new FileOutputStream(fileName);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 1000000; i++) {
            int a = (int)(Math.random() * 100);
            sb.append(a).append(",");
        }
        os.write(sb.toString().getBytes());
        os.close();
    }
}