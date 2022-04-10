package com.example.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.text.UnicodeUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.json.JSONArray;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: luozijian
 * @date: 2021/12/16 10:41:26
 * @description:
 */
public class Hutools {

    /**
     * 身份证校验
     */
    public static final Pattern CHINIDENTITYCARDNUMBERESES = Pattern.compile("^\\d{17}([0-9]|X)$");

    /**
     * 营业执照统一社会信用代码
     */
    private static final Pattern CREDITCODE = Pattern.compile("[0-9A-Za-z]{18}");

    @Test
    public void test() {
        //身份证号码，0-18位，数字+字母
        System.out.println(Validator.isMatchRegex(CHINIDENTITYCARDNUMBERESES, "440981199182320901"));

        System.out.println(Validator.isMatchRegex(CREDITCODE, "440981Q9918232090A"));

        LocalDateTime time = DateUtil.parseLocalDateTime("2021-12-11 00:00", "yyyy-MM-dd HH:mm");
        System.out.println(time.toInstant(ZoneOffset.of("+8")).getEpochSecond());

        System.out.println(System.currentTimeMillis() / 1000);
        System.out.println(Instant.now().getEpochSecond());

        String storeCode = MessageFormat.format("{0}{1}{2}", "BEI", 14444L, "S");
        String storeCode1 = "BEI" + 15555L + "S";
        System.out.println(storeCode);
        System.out.println(storeCode1);


        BigDecimal amount = new BigDecimal("0.5");

        int i = amount.compareTo(BigDecimal.ZERO);
        if (i == -1) {
            //amount小于0  例如：amt=-10.00
        }
        if (i == 0) {
            //amount等于0，  amt=0.00
        }
        if (i == 1) {
            //amount大于0  例如：amt=10.00
        }

    }


    @Test
    public void desensitizedUtil() {
        String chineseName = desentize("罗子");
        System.out.println(chineseName);
    }

    @Test
    public void idCardNum() {

        StringBuffer sb = new StringBuffer("123");
        for (int i = 4; i < 18; i++) {

            System.out.println("size=" + sb.length() + ": " + desentize(sb.toString()));
            sb.append(i % 10);
        }


    }

    @Test
    public void mobilePhone() {
        String s = DesensitizedUtil.mobilePhone("18049531999");
        System.out.println(s);
    }


    public static String desentize(String num) {
        if (StringUtils.isEmpty(num)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (num.length() < 7) {
            sb.append(num).append("******").append(num);
        } else {
            String front = num.substring(0, 6);
            String end = num.substring(num.length() - 6, num.length());
            sb.append(front).append("******").append(end);
        }
        return sb.toString();
    }


    @Test
    public void test11() {
        System.out.println(replaceNameX("罗子建"));
    }

    public String replaceNameX(String str) {
        String reg = ".{1}";
        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        int i = 0;
        while (m.find()) {
            i++;
            if (i == 1)
                continue;
            m.appendReplacement(sb, "*");
        }
        m.appendTail(sb);
        return sb.toString();
    }


    @Test
    public void test3() {
        System.out.println(nameDesensitization("慕容工资成个"));
    }

    public static String nameDesensitization(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }
        String myName = null;
        char[] chars = name.toCharArray();
        if (chars.length == 1) {
            myName = name;
        }
        if (chars.length == 2) {
            myName = name.replaceFirst(name.substring(1), "*");
        }
        if (chars.length > 2) {
            myName = name.replaceAll(name.substring(1, chars.length - 1), "*");
        }
        return myName;
    }


    @Test
    public void test34() {
        LocalDate localDate = LocalDate.parse("2021-12-11", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(localDate.toString());

    }


    @Test
    public void test4() {
        List list1 = Lists.newArrayList();
        list1.add(A.builder().id("1").name("张三").build());
        list1.add(A.builder().id("2").name("张三2").build());

        List list2 = Lists.newArrayList();
        list2.add(A.builder().id(null).name("张三").build());
        list2.add(A.builder().id(null).name("张三3").build());

        List<A> intersection = (List<A>) CollectionUtils.intersection(list1, list2);
        List<A> intersection1 = (List<A>) CollectionUtils.intersection(list2, list1);

        System.out.println(intersection);

        System.out.println(intersection1);


    }


    @Test
    public void copy() {

        A a = A.builder().id("2").build();
        B b = B.builder().build();
        BeanUtil.copyProperties(a, b);
        System.out.println(b.getId());

//        System.out.println("AB".substring(0, 3));

        //MD5加密 8df68096d3aab587b50e14affbd4bde8
        String str = "[[\"1\",\"\"]]";
        StringBuilder sb = new StringBuilder("1").append("-");
        String md5Str = SecureUtil.md5(str);
        System.out.println("secureUtil md5: " + md5Str);

        System.out.println(SecureUtil.md5(sb.toString()));

    }
    

    @Test
    public void test44(){
        LocalDate localDate = LocalDate.now();
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println("week name:"+dayOfWeek.name());
        System.out.println("FULL:"+ dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        System.out.println("NARROW:"+ dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.ENGLISH));
        System.out.println("FULL_STANDALONE:"+dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, Locale.ENGLISH));
        System.out.println("SHORT:"+dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        System.out.println("SHORT_STANDALONE:"+dayOfWeek.getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH));
        System.out.println("NARROW_STANDALONE:"+dayOfWeek.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.ENGLISH));

        System.out.println(PinyinUtil.getPinyin("罗子健", ""));

        char[] chars = {(char)160, '1', '2', (char)160, '3'};
//        System.out.println(new String(chars).replace((char)160, ' ').trim());

        System.out.println(StrUtil.removeAll(new String(chars), (char)160));

    }

    @Test
    public void test42(){

        LocalDateTime localDateTime = LocalDateTime.parse("2022-01-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(localDateTime.toInstant(ZoneOffset.of("+8")).getEpochSecond());
        System.out.println(localDateTime.toInstant(ZoneOffset.of("+0")).getEpochSecond());

        Float a = 3F;
        Float b = a * 60;
        System.out.println(a > 0F);

        BigDecimal dailyPrice = BigDecimal.ZERO;
        dailyPrice = dailyPrice.add(BigDecimal.valueOf(12));
        System.out.println(dailyPrice);

        BigDecimal totalHourPrice = new BigDecimal("100")
                .multiply(new BigDecimal(a/100));

        System.out.println(totalHourPrice);
        System.out.println(totalHourPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString());



    }




    

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class A {
        private String id;
        private String name;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            A a = (A) o;
            return getName().equals(a.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName());
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class B {
        private String id;
    }
}