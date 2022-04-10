package com.example.suanfa.digui;

import org.junit.Test;

/**
 * @author: luozijian
 * @date: 6/27/21 14:48:04
 * @description:
 */
public class DiguiTest {

    public static void main(String[] args) {
//        factrialDetail(3);

        System.out.println("最后结果：" + fibonacci(5));
    }

    public static int factrialDetail(int n){
        if(n<1){
//            System.out.println("拆解问题完毕，开始分而治之");
            return 1;
        }
//        System.out.println("f("+n+")="+n+" * f("+(n-1)+")");
        int z= n*factrialDetail(n-1);

        System.out.println("f("+n+")="+z);

        int z1= n*factrialDetail(n-2);

        return  z;

    }


    @Test
    public void testFibonacci(){
        System.out.println("最后结果：" + fibonacci(5));
    }

    public static int fibonacci(int n){
        if(n<0){
            throw new IllegalArgumentException("传入参数不合法");
        }
        if(n<=2) {
            return 1;
        }

        //先计算第一个递归函数.
        int plusItem1 = fibonacci(n-1);

        System.out.println("n=" + n + "， plusItem1=" + plusItem1);

        int plusItem2 = fibonacci(n-2);

        System.out.println("n=" + n + "， plusItem2=" + plusItem2);

        int sum = plusItem1+plusItem2;

        System.out.println("n=" + n + "， plusItem1=" + plusItem1 + ",plusItem2=" + plusItem2 + ",sum=" + sum );

        return sum;

    }


    @Test
    public void testSlicePie(){
        System.out.println(slicePie(4));
    }
    /**
     * 切饼
     * f(n) = f(n-1) + n
     * @param n
     * @return
     */
    public static int slicePie(int n){
        if(n == 0){
            return 1;
        }
        int sum = slicePie(n-1) + 1;
        return sum;
    }


}