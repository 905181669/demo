package com.example.algorithm;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 2020-05-26 20:04:39
 * @description:
 */
public class CommonDiv {


    @Test
    public void maxCommonDiv(){

        System.out.println(maxCommonDiv(8,6));
    }



    private int maxCommonDiv(int a, int b){
        if(a==b){
            return a;
        }
        int small, big;
        small = a > b ? b : a;
        big = a > b ? a : b;

        int r = big % small;
        if(r == 0){
            return small;
        }

        return maxCommonDiv(r, small);

    }

}
