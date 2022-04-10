package com.example.gc;

/**
 * @author: luozijian
 * @date: 2022/3/5
 * @description:
 *
 * -Xmx100M
 * -Xms100M
 * -XX:+UseConcMarkSweepGC
 * -XX:-UseCMSCompactAtFullCollection
 * -XX:CMSInitiatingOccupancyFraction=68
 * -XX:+UseCMSInitiatingOccupancyOnly
 * -XX:CMSFullGCsBeforeCompaction=10
 * -XX:PretenureSizeThreshold=1m  对象大于1m的直接在老年代分配
 * -XX:+PrintGCDetails
 *
 * 测试CMS并发模式失败
 *
 */
public class CMSTest {

    public static void main(String[] args) throws Exception {

        while(true) {
            Thread.sleep(500);
            int[] a = new int[1024 * 1024 * 2]; //2M
        }
    }

}