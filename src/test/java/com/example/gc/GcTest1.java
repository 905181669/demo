package com.example.gc;

/**
 * @author: luozijian
 * @date: 1/25/21 21:50:58
 * @description:
 */
public class GcTest1 {

    private static final int _1MB = 1024*1024;

    /**
     * 其实只要设置-XX:+PrintGCDetails 就会自动带上-verbose:gc和-XX:+PrintGC
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *
     * 年轻代：eden(8192K) + S0(1024) =  9216K
     * 整个堆：10240-1024(其中s1不算)=19456K
     *
     * [GC (Allocation Failure) [PSYoungGen: 7586K(年轻代垃圾回收前的大小)->1001K(年轻代垃圾回收后的大小)
     * (9216K)(这个年轻代大小)]
     * 7586K(整个堆回收前的大小)->3387K(整个堆回收后的大小)(19456K)(整个堆大小), 0.0128621 secs]
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; //出现一次minor GC

        /**
         * 当分配allocation4时，由于eden没有空间分配4M给它了，因此发生一次minor GC，
         * 但是s空间只有1M，无法放入allocation1-3，因此通过担保机制把allocation1-3提前转移到了
         * 老年代。因此minor GC结束后，Eden占4M，老年代占6M
         */

    }
}
