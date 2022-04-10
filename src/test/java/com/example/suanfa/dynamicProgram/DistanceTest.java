package com.example.suanfa.dynamicProgram;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 7/1/21 16:19:35
 * @description:
 */
public class DistanceTest {


    @Test
    public void test(){

        int[] stores = {1,5,20,11,16};
//        int[] stores = {1,5};
        int[] sites = {5,10,18};

//        int[] res = findShortestPoint(stores, sites);
        int[] res = findShortestPoint1(stores, sites);
        System.out.println(Arrays.toString(res));


    }


    public int[] findShortestPoint(int[] stores, int[] sites){

        //定义dp数组，定义：dp[i]是第i个site与stores最近的距离
        int[] dp = new int[sites.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int[] res = new int[sites.length];

        for(int i = 0; i < sites.length; i++){

            for(int j = 0; j < stores.length; j++){

                //记录上一个dp值
                int prev = dp[i];
                dp[i] = Math.min(Math.abs(sites[i] - stores[j]), dp[i]);

                //有变更，记录j点
                if(prev != dp[i]){
                    res[i] = stores[j];
                }

            }
        }
        return res;
    }


    /**
     * 比版本多了一个排序
     * @param stores
     * @param sites
     * @return
     */
    public int[] findShortestPoint1(int[] stores, int[] sites){

        Arrays.sort(stores);
        Arrays.sort(sites);
        //定义dp数组，定义：dp[i]是第i个site与stores最近的距离
        int[] dp = new int[sites.length];
        Arrays.fill(dp, Integer.MAX_VALUE);

        int[] res = new int[sites.length];

        for(int i = 0; i < sites.length; i++){

            for(int j = 0; j < stores.length; j++){

                //记录上一个dp值
                int prev = dp[i];
                dp[i] = Math.min(Math.abs(sites[i] - stores[j]), dp[i]);

                //有变更，记录j点
                if(prev != dp[i]){
                    res[i] = stores[j];
                }

            }
        }

        return res;

    }





}