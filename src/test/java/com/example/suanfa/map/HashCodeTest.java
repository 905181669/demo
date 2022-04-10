package com.example.suanfa.map;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: luozijian
 * @date: 7/4/21 19:26:28
 * @description:
 */
public class HashCodeTest {

    /**
     * 思考：为什么HashMap获取数组下标时需要先位移16位然后异或-->再与size做与操作？
     * 1.将key的hashCode先位移16位再与原hashCode做异或，这样key的hashCode的高低位都可以参与到后面的与运算，
     * 不然如果直接拿key的hashCode与size做与运算，假设map的size为16，那hashCode的高位基本就参与不到与运算中，
     * 只有低4位参与，
     * 这样hashCode真正生效的只有低4位，加大了碰撞的可能
     *
     * 原key hashCode: 0000 0010 1100 1000 1100 1010 1100 0011
     * 位移16位：       0000 0000 0000 0000 0000 0010 1100 1000
     *
     * 异或运算：       0000 0010 1100 1000 1100 1000 0000 1011
     *
     * 与size-1的值    0000 0000 0000 0000 0000 0000 0000 1111
     *
     * &运算得出数组下标:                                   1011
     *
     * 1011转为十进制为11
     *
     *
     * 结论：使用扰动函数就是为了增加随机性，让数据元素更加均衡的散 列，减少碰撞。
     * @param args
     *
     */

    private final int threadLocalHashCode = nextHashCode();

    private static final int HASH_INCREMENT = 0x61c88647;

    /**
     * Returns the next hash code.
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    private static AtomicInteger nextHashCode =
            new AtomicInteger();

    public static void main(String[] args) {

        String a = "luozjian";
        String b = "luozjiam";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());

        int size = 10;
        for(int i = 0; i < size; i++){
            System.out.println(i & 7);
        }


        System.out.println("-------");
        for(int i = 0; i < size; i++){
            System.out.println(i % 8);
        }

        //取模运行可转为与运算，与运算更快，i & (size-1) == i % size

        System.out.println("-------");
        for(int i = 0; i < size; i++){
            System.out.println(i ^ 7);
        }

        /**
         * 异或运算：相同为0，不同为1
         * a ^ 0 = a
         * a ^ a = 0
         * a ^ (b ^ b) = a ^ 0 = a
         */



        System.out.println("8>>1: " + (8 >> 1)); //右移1位=除以2 = 4
        System.out.println("7>>1: " + (7 >> 1)); //3


        System.out.println("-------");
        HashCodeTest test = new HashCodeTest();
        System.out.println(test.threadLocalHashCode);
        System.out.println(test.threadLocalHashCode);
    }



    @Test
    public void testTableSizeFor(){
        System.out.println(tableSizeFor(18));
    }

    public int tableSizeFor(int cap) {
        int n = cap-1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >=  1 << 30) ?  1 << 30 : n + 1;
    }


    @Test
    public void test_hashMap() {
        List<String> list = new ArrayList<>();

        list.add("jlkk");

        for (String key : list) {
            int hash = key.hashCode() ^ (key.hashCode() >>> 16);
            System.out.println("key.hashCode: " + Integer.toBinaryString(key.hashCode())); //1100011101001000100010
            System.out.println("hash:         " + Integer.toBinaryString(hash));           //1100011101001000010011

            System.out.println("oldCap:       " + Integer.toBinaryString(16));
            System.out.println("old index:    " + (hash & 15));
            //如果 hash & oldCap = 1，则新位置=旧位置+16
            System.out.println("hash & oldCap:" + Integer.toBinaryString(hash & 16));

            System.out.println("new index:    " + (hash & 31));

        }

    }


    /**
     * 树化，如果数组长度< 64，则优先扩展数组而不是树化
     */
    @Test
    public void treeifyBin(){

        /**
         * zuio和iu8e的hash一样，这样在index=8时就构成链表
         */
        HashMap map = new HashMap<>();
        map.put("zuio", 1);
        map.put("iu8e", 2);
        map.put("yhjk", 3);

        map.get("yhjk");


    }


    @Test
    public void test_idx() {

        final int HASH_INCREMENT = 0x61c88647;

        int hashCode = 0;

        AtomicInteger nextHashCode =
                new AtomicInteger();
        for (int i = 0 ; i < 16 ; i++){

//            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;


            hashCode = nextHashCode.getAndAdd(HASH_INCREMENT);


            int idx = hashCode & 15;

            /**
             * 斐波那契散列得出的冲突很小
             */
            System.out.println("hashCode: " + hashCode +  " 斐波那契散列:" + idx
                    + " 普通散列: " + (String.valueOf(i).hashCode() & 15));
        }
    }



    @Test
    public void test_threadLocalHashCode() throws Exception {

        for (int i=0;i<5;i++){

            ThreadLocal<Object> objectThreadLocal = new ThreadLocal<>();

            Field threadLocalHashCode = objectThreadLocal.getClass()
                    .getDeclaredField("threadLocalHashCode");

            threadLocalHashCode.setAccessible(true);
            System.out.println("objectThreadLocal: " + threadLocalHashCode.get(objectThreadLocal));
        }

        Map map = Maps.newHashMapWithExpectedSize(2);
        map.put(1,1);
    }



}