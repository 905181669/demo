package com.example.suanfa;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 2/5/21 11:24:06
 * @description:
 */
public class DiguiTest {

    public static void main(String[] args) {
//        findSingle();

        String[] str = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(str));

        System.out.println("abc".indexOf("abcd"));

    }

    public static int findSingle(){

        /**
         * 直接对数组中每个值异或操作，成对的数都将被消除掉
         * 给出2*n + 1 个的数字，除其中一个数字之外其他每个数字均出现两次，找到这个数字。
         */
        int [] test = {1,2,2,1,3,4,3};
        int result = 0;
        for (int i = 0; i < test.length; i++) {
            result = (result ^ test[i]);

        }
        System.out.println(result);


        return result;
    }


    /**
     * 判断needle是否haystack的子串
     * 双指针
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle){

        int len_H = haystack.length();
        int len_N = needle.length();
        if(len_N==0)
            return 0;
        int i=0;
        int j=0;
        while(i<len_H && j<len_N){
            if(haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

            }else{
                //双指针的精辟之处,其实和下面的暴力解法异曲同工
                i = i-j+1;
                j = 0;
            }

        }
        if(j==len_N){
            return i-j;
        }

        return -1;
    }


    /**
     * 暴力
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr1(String haystack, String needle){

        int n = haystack.length();
        int m = needle.length();

        for(int i = 0; i < n-m; i++){

            boolean flag = true;
            for(int j = 0; j < m; j++){
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }

        return -1;


    }


    /**
     * 最长公共前缀
     * @param strs
     * @return
     */
    public  static String longestCommonPrefix(String[] strs) {
        //边界条件判断
        if (strs == null || strs.length == 0)
            return "";
        //默认第一个字符串是他们的公共前缀
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            //不断的截取
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
    }


    @Test
    public void test(){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                int a = (i/3)*3 + j/3;
                int b = (i%3)*3 + j%3;
                System.out.println("a=" + a + ", b=" + b);
            }
        }
    }


    /**
     * 阶乘
     */
    @Test
    public void jiecheng(){
        System.out.println(jiecheng(3));
    }

    private int jiecheng(int n){
        if(n <= 1){
            return 1;
        }
        return n * jiecheng(n-1);
    }

    @Test
    public void test1(){
        digui(8);
    }

    private void digui(int n){
        if(n == 0){
            return;
        }
        System.out.println(n);
        digui(n-1);
        System.out.println(n);

    }


}
