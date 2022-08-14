package com.example.demo;

import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.collections.ListUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.digester.Digester;
import org.openjdk.jol.info.ClassLayout;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.util.CollectionUtils;


import javax.annotation.PostConstruct;
import java.io.*;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.net.URL;
import java.text.Collator;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author: luozijian
 * @date: 2020-04-21 11:21:09
 * @description:
 */
public class Test implements Serializable {

    @org.junit.Test
    public void test1(){
        addBinary("011", "11");
    }

    public String addBinary(String a, String b) {
        int n = a.length() - 1;
        int m = b.length() - 1;
        int carry = 0;
        StringBuilder  sb = new StringBuilder();
        while (n >= 0 || m >= 0 || carry != 0) {
            int sum = carry;
            if (n >= 0) {
                sum += a.charAt(n) - '0';
                n--;
            }
            if (m >= 0) {
                sum += b.charAt(m) - '0';
                m--;
            }

            sb.append(sum % 2);
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }


    @org.junit.Test
    public void test2(){
        countOnes(3);
    }

    public int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }

    @org.junit.Test
    public void test3(){
        //-XX:-UseCompressedClassPointers -XX:-UseCompressedOops
        System.out.println(ClassLayout.parseInstance(new T()).toPrintable());
    }

    @org.junit.Test
    public void test4(){
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int max = 1;
        int[] dp = new int[len];
        dp[0] = 1;

        for(int i = 1; i < len; i++) {
            int count = 1;
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    count++;
                }
            }
            dp[i] = count;
        }
        Arrays.sort(dp);
        return dp[len-1];
    }


    @org.junit.Test
    public void test5(){
        PriorityQueue<Integer> queue = new PriorityQueue();
        queue.offer(100);
        queue.offer(10);
        queue.offer(1);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

}


class T{
    int a;
    int b;
    boolean flag;
    String str = "hello";
}
