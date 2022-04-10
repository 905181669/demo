package com.example.collection;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * @author: luozijian
 * @date: 2/5/21 11:04:09
 * @description:
 */
public class MapTest {

    public static void main(String[] args) {
        HashMap map = Maps.newHashMap();

        System.out.println(indexFor(6, 8));
        System.out.println(indexFor(6, 8-1));


        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put(null, 1);

//        concurrentHashMap.put(1, "a");
//        concurrentHashMap.size();

        map.put(1, null);

        map.entrySet().forEach((e)->{
            System.out.println(e);
        });

        System.out.println(map.getOrDefault(1, "b"));
    }

    /**
     * return h & (length-1);只要保证length的长度是2^n的话，就可以实现取模运算了。
     * 而HashMap中的length也确实是2的倍数，初始值是16，之后每次扩充为原来的2倍;
     *
     * HashMap默认的初始化大小为16，之后每次扩充为原来的2倍。
     * HashTable默认的初始大小为11，之后每次扩充为原来的2n+1。
     * 当哈希表的大小为素数时，简单的取模哈希的结果会更加均匀，所以单从这一点上看，HashTable的哈希表大小选择，似乎更高明些。因为hash结果越分散效果越好。
     * 在取模计算时，如果模数是2的幂，那么我们可以直接使用位运算来得到结果，效率要大大高于做除法。所以从hash计算的效率上，又是HashMap更胜一筹。
     * 但是，HashMap为了提高效率使用位运算代替哈希，这又引入了哈希分布不均匀的问题，所以HashMap为解决这问题，又对hash算法做了一些改进，进行了扰动计算。
     * @param h
     * @param length
     * @return
     */
    static int indexFor(int h, int length) {
        return h & (length-1);
    }
}
