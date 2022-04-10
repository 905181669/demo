package com.example.classTest;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: luozijian
 * @date: 2/20/21 12:34:52
 * @description:
 */
public class Hello {

    static double sum = 0;
    static double koujiansum = 0;
    static double leijiyingnashui = 0;

    public static void main(String[] args) {

        double[] zhuanxian = {1551.94, 1551.94, 1551.94, 1551.94,
                1551.94, 1551.94, 1551.94, 1626.38, 1626.38, 1614.74, 1614.74, 1614.74};


        double[] gongzi = {13970, 19970, 13990, 13990, 14015,
                14040, 25290, 14165, 16984, 18415, 16690, 16590};


        for(int i = 0; i < 12; i++) {
//            System.out.println((i+1) + "月计算后的申报税额：" + cal(i+1, gongzi[i], zhuanxian[i]));
        }

        double[] gongzi1 = {24970, 24970, 24990, 24990, 24015,
                24040, 25290, 24165, 24984, 24415, 24690, 24590};



        for(int i = 0; i < 12; i++) {
            System.out.println((i+1) + "月：收入："+gongzi1[i] +"，已申报税额：" + cal(i+1, gongzi1[i], zhuanxian[i]));
        }


        for(int i = 0; i < 12; i++) {
            sum += gongzi[i];
            koujiansum += zhuanxian[i];

        }
        System.out.println(sum);
        System.out.println(koujiansum);
        System.out.println(sum - koujiansum - 60000);


        System.out.println("--------------");
        sum = 0;
        koujiansum = 0;
        for(int i = 0; i < 12; i++) {
            sum += gongzi1[i];
            koujiansum += zhuanxian[i];
        }
        System.out.println(sum);
        System.out.println(koujiansum);
        System.out.println(sum - koujiansum - 60000);

        System.out.println("---------下面是2022年1，2------");

        double[] gongzi2022real = {16730, 16715};
        double[] koujian2022real = {1615.14, 1626.78, 1626.78};


        sum = 0;
        koujiansum = 0;
        leijiyingnashui = 0;
        for(int i = 0; i < 2; i++) {
//            System.out.println((i+1) + "月计算后的申报税额：" + cal(i+1, gongzi2022real[i], koujian2022real[i]));
        }


        double[] gongzi2022fake = {24730, 24715, 24490};

        sum = 0;
        koujiansum = 0;
        leijiyingnashui = 0;
        for(int i = 0; i < 3; i++) {
            System.out.println("2022 " + (i+1) + "月计算后的申报税额：" + cal(i+1, gongzi2022fake[i], koujian2022real[i]));
        }

    }

    public static String cal(int month, double gongzi, double koujian) {
        sum += gongzi;
        koujiansum += koujian;
        leijiyingnashui = sum - koujiansum - (month * 5000);

        double ret = 0;
        if (leijiyingnashui < 36000) {
            ret = (gongzi - 5000 - koujian) * 0.03;
        }else if(leijiyingnashui < 144000) {
            ret = (gongzi - 5000 - koujian) * 0.1;
        }else if(leijiyingnashui < 300000) {
            ret = (gongzi - 5000 - koujian) * 0.2;
        }

        return String.format("%.2f" , ret);
    }

    static class Tree{
        String name = "ah";
    }


    @Test
    public void test(){

        // 从2021-01至2021-12
        double[] gongzi2021fake = {24970, 24970, 24990, 24990, 24015,
                24040, 25290, 24165, 24984, 24415,
                24690, 24590};
//        System.out.println(Arrays.stream(gongzi2021fake).sum());

        //已申报税额
        double[] shuie2021fake = {552.54, 1841.81, 1843.81, 1843.81, 1746.31,
                1748.81, 1873.81, 3507.72, 3671.52, 3560.05,
                3615.05, 3595.05};

//        System.out.println(Arrays.stream(shuie).sum());

        double[] shuie2021true = {222.54, 402.54, 223.15,223.14, 726.16,748.81,1873.80,753.86,1035.77,1180.02,1007.53,997.52};
//        System.out.println(Arrays.stream(shuie1).sum());

        double[] gongzi2021true = {13970, 19970, 13990, 13990, 14015,
                14040, 25290, 14165, 16984, 18415, 16690, 16590};

        double[] zhuanxian = {1551.94, 1551.94, 1551.94, 1551.94,
                1551.94, 1551.94, 1551.94, 1626.38, 1626.38, 1614.74, 1614.74, 1614.74};



        for(int i =0; i < 12; i++) {
            System.out.print( "真2021年"+ (i+1) +"月：" + (gongzi2021true[i] - shuie2021true[i] - zhuanxian[i]));
            System.out.print("  ");
            System.out.print( "假2021年"+ (i+1) +"月：" + (gongzi2021fake[i] - shuie2021fake[i] - zhuanxian[i]));
            System.out.println("");
        }

        //计算实际收入


    }

    @Test
    public void test1(){

        // 从2022-01至2022-03
        double[] gongzi1 = {24730, 24715, 24490};

        // 已申报税额
        double[] shuie = {543.44, 1808.82, 1786.32};

        double[] koujian2022real = {1615.14, 1626.78, 1626.78};

        //计算实际收入
        for(int i =0; i < 3; i++) {
            System.out.println( "2022年"+ (i+1) +"月：" + (gongzi1[i] - shuie[i] - koujian2022real[i]));
        }

        double[] gongzi1fake = {16730, 16715, 16490};
        double[] shuiefake = {303.45, 302.64, 295.9};
        for(int i =0; i < 3; i++) {
            System.out.println( "2022年"+ (i+1) +"月：" + (gongzi1fake[i] - shuiefake[i] - koujian2022real[i]));
        }

    }

}
