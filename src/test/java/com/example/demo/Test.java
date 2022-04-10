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


    private static int count = 0;
    public static void recursion(){
        count ++;
        recursion();
    }

    public static void main (String args[]){

//        System.out.println(new Test().hashCode());

//        System.out.println(Integer.valueOf("1006", 16));

        Random random = new Random();
        System.out.println(random.nextInt(20));

        System.out.println(Long.MAX_VALUE/1000000/60/60/24/356);

    }


    @org.junit.Test
    public void test1() throws Exception{


//        Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.example.demo.Function");

//        Class.forName("com.example.demo.Function");


        ArrayBlockingQueue queue = new ArrayBlockingQueue(1);
        System.out.println(queue.offer(1));
        System.out.println(queue.offer(2));
//        queue.put(1);
//        queue.put(2);
//        System.out.println(queue.poll());
//        System.out.println(queue.take());

        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }


    @org.junit.Test
    public void test2(){
        List v = new ArrayList();
        for(int i = 0; i < 25; i++){
            v.add(new byte[1024*1024]);
            v.add(new Book());
//            v.add(new DogBook());
        }
    }


    @org.junit.Test
    public  void test4() throws Exception{
        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        SoftReference<byte[]> cacheRef = new SoftReference<>(cacheData);
        //将缓存数据的强引用去除
        cacheData = null;
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        //在分配一个120M的对象，看看缓存对象的回收情况
        byte[] newData = new byte[120 * 1024 * 1024];
        System.out.println("分配后" + cacheData);
        System.out.println("分配后" + cacheRef.get());
    }


    @org.junit.Test
    public void test5() throws Exception{
        //100M的缓存数据
        byte[] cacheData = new byte[100 * 1024 * 1024];
        //将缓存数据用软引用持有
        WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);
        System.out.println("第一次GC前" + cacheData);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + cacheData);
        System.out.println("第一次GC后" + cacheRef.get());

        //将缓存数据的强引用去除
        cacheData = null;
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第二次GC后" + cacheData);
        System.out.println("第二次GC后" + cacheRef.get());
    }



    @org.junit.Test
    public void test6(){

        Map map = Maps.newHashMap();
        String key = "key";
        map.put(key, key);
        key = null;
        System.out.println(map.get("key") == key);


    }

    @org.junit.Test
    public void stopTheWorldTest(){

        String a = "1";
        String b = a;
        System.out.println(a);
        a = "2";
        System.out.println(a);
        System.out.println(b);
    }


    @org.junit.Test
    public void test7(){

        int threadNum = 0;
        try {
            while (true) {
                threadNum++;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        doSomething();

                    }
                });
                thread.start();
            }
        } catch (Throwable e) {
            System.out.println("目前活动线程数量：" + threadNum);
            throw e;
        }
    }

    public void doSomething() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void test9(){
        try {
            while (true){
                Enhancer enhancer=new Enhancer();
                enhancer.setSuperclass(Test.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,objects);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable t){
            t.printStackTrace();
        }
    }


    @org.junit.Test
    public void test10(){

        /**
         * -XX:NewSize=5242880
         * -XX:MaxNewSize=5242880
         * -XX:InitialHeapSize=10485760
         * -XX:MaxHeapSize=10485760
         * -XX:SurvivorRatio=8
         * -XX:PretenureSizeThreshold=10485760
         * -XX:+UseParNewGC
         * -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails
         * -XX:+PrintGCTimeStamps
         */


        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];

    }


    @org.junit.Test
    public void test11(){
        /**
         * -XX:NewSize=10485760 10M
         * -XX:MaxNewSize=10485760
         * -XX:InitialHeapSize=20971520 20M
         * -XX:MaxHeapSize=20971520
         * -XX:SurvivorRatio=8
         * -XX:PretenureSizeThreshold=10485760
         * -XX:+UseParNewGC
         * -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails
         * -XX:+PrintGCTimeStamps
         * -XX:+PrintCommandLineFlags
         * -XX:MaxTenuringThreshold=15
         */

//        byte[] array1 = new byte[1 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = new byte[2 * 1024 * 1024];
//        array1 = null;
//
//        byte[] array2 = new byte[128 * 1024];
//
//        byte[] array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = new byte[2 * 1024 * 1024];
//        array3 = null;
//
//        byte[] array4 = new byte[2 * 1024 * 1024];



    }


    @org.junit.Test
    public void test12(){

        byte[] array1 = new byte[4 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[2 * 1024 * 1024];
        byte[] array3 = new byte[2 * 1024 * 1024];
        byte[] array4 = new byte[2 * 1024 * 1024];
        byte[] array5 = new byte[128 * 1024];

        byte[] array6 = new byte[2 * 1024 * 1024];


    }


    @org.junit.Test
    public void test13() throws Exception{

        /**
         * -XX:NewSize=104857600
         * -XX:MaxNewSize=104857600
         * -XX:InitialHeapSize=209715200
         * -XX:MaxHeapSize=209715200
         * -XX:SurvivorRatio=8
         * -XX:MaxTenuringThreshold=15
         * -XX:PretenureSizeThreshold=3145728
         * -XX:+UseParNewGC
         * -XX:+UseConcMarkSweepGC
         * -XX:+PrintGCDetails
         * -XX:+PrintGCTimeStamps
         */

        Thread.sleep(30000);
        while (true){
            loadData();
        }



    }

    private static void loadData() throws Exception{
        byte[] data = null;
        for(int i = 0 ; i < 50; i++){
            data = new byte[100 * 1024];

        }
        data = null;
        Thread.sleep(1000);
    }


    @org.junit.Test
    public void test14() throws Exception{

        Thread.sleep(30000);
        while (true){
            loadData14();
        }
    }

    private static void loadData14() throws Exception{
        byte[] data = null;
        for(int i = 0 ; i < 4; i++){
            data = new byte[10 * 1024 * 1024];

        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];

        byte[] data2 = new byte[10 * 1024 * 1024];

        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];

        Thread.sleep(1000);
    }



    @org.junit.Test
    public void test() throws Exception{

        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet);

        System.out.println(response.toString());

    }


    @org.junit.Test
    public void testOutput(){

        String text = "1c子";
//        System.out.println(text.getBytes().length);
//
//        System.out.println(Arrays.toString(text.getBytes()));


        System.out.println(str2HexStr("罗子健"));


        System.out.println(hexStr2Str(str2HexStr("罗子健")));

    }

    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }



    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }


    /**
     * 值传递
     */
    @org.junit.Test
    public void passTest(){

        String s1 = new String("1");
        System.out.println(s1.hashCode());
        change(s1);
        System.out.println(s1.hashCode());

        System.out.println((char)1020);


    }

    public void change(String s){
        s = new String("2");
    }

    @org.junit.Test
    public void testChar(){

        //unicode码
        System.out.println(Integer.toHexString('马'));

        String hex = Integer.toHexString('马');
        hex = "0x" + hex;

//        String s = "0x41";
        String s = hex;
        int b = Integer.parseInt(s.replaceAll("^0[x|X]", ""), 16);
        System.out.println(b);
        System.out.println((char)b);

//        char newChar = (char)65535;
        char newChar = (char)1300;
        System.out.println((char)39532);
        System.out.println(newChar);

    }


    @org.junit.Test
    public void testList(){

        ArrayList list = Lists.newArrayList("1");
        Iterator i = list.iterator();
        System.out.println(list.size());


    }

    @org.junit.Test
    public void testTreeMap(){

        Map<String, String> map = new TreeMap<String, String>((o1, o2)->chineseToLetter(o1).compareTo(chineseToLetter(o2)));


        map.put("ZZC_T2", "ZZC_T2");
        map.put("ZZCT2", "ZZCT2");
        map.put("Z_ZCT2", "ZZCT2");

        System.out.println((int)'_');
        System.out.println((int)'Z');

        Set<Map.Entry<String, String>> s = map.entrySet();
        Iterator<Map.Entry<String, String>> i = s.iterator();
        while(i.hasNext()){
            Map.Entry<String, String> e = i.next();
            System.out.println(e.getKey() + " : " + e.getValue());
        }


    }



        public static String chineseToLetter(String chinese) {
            if(chinese == null){
                return "";
            }
            chinese = chinese.toUpperCase();
            //输出格式设置
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            /**
             * 输出大小写设置
             * LOWERCASE:输出小写
             * UPPERCASE:输出大写
             */
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);

            /**
             * 输出音标设置
             *
             * WITH_TONE_MARK:直接用音标符（必须设置WITH_U_UNICODE，否则会抛出异常）
             * WITH_TONE_NUMBER：1-4数字表示音标
             * WITHOUT_TONE：没有音标
             */
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

            /**
             * 特殊音标ü设置
             *
             * WITH_V：用v表示ü
             * WITH_U_AND_COLON：用"u:"表示ü
             * WITH_U_UNICODE：直接用ü
             */
            format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);

            char[] hanYuArr = chinese.trim().toCharArray();
            StringBuilder pinYin = new StringBuilder();

            try {
                for (int i = 0, len = hanYuArr.length; i < len; i++) {
                    //匹配是否是汉字
                    if (Character.toString(hanYuArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                        //如果是多音字，返回多个拼音，这里只取第一个
                        String[] pys = PinyinHelper.toHanyuPinyinStringArray(hanYuArr[i], format);
                        pinYin.append(pys[0]);
                    } else {
                        pinYin.append(hanYuArr[i]);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            return pinYin.toString();
        }



        @org.junit.Test
        public void testExecutor() throws Exception{
            ExecutorService executorService = new ThreadPoolExecutor(1, 1, 60L,
                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory());


            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + ": 测试1");
            });

            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + ": 测试2");
            });


        }


        @org.junit.Test
        public void testAdd(){



            BigDecimal tmp = new BigDecimal(1 - (double) 9001/10000);
            tmp = tmp.multiply(new BigDecimal(100));

            Double result = tmp.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

            System.out.println(result.toString());
        }


        @org.junit.Test
        public void testFor(){
            for(int i = 0; i < 10; ++i){
                System.out.println(i);
            }

        }


        @org.junit.Test
        public void testMap(){
            Map<String, String> AGENT_CHANNEL_MAP = Maps.newConcurrentMap();
            AGENT_CHANNEL_MAP.put("8301_abc", "8301_abc");
            AGENT_CHANNEL_MAP.put("8301_dfs", "8301_dfs");
            AGENT_CHANNEL_MAP.put("12000_cab", "12000_cab");

            String agentId = "8301";
            String key = "8301_abc";
//            AGENT_CHANNEL_MAP.entrySet().removeIf(entry->entry.getKey().startsWith(agentId) && !entry.getKey().equals(key));

            AGENT_CHANNEL_MAP.entrySet().removeIf(entry->entry.getKey().equals(key));
            System.out.println(AGENT_CHANNEL_MAP);
        }


        @org.junit.Test
        public void testFuture(){
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {

                try{
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println("Hello");
            });

            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.println("CompletableFuture");
        }


        @org.junit.Test
        public void testFile() throws Exception{
            FileInputStream is = new FileInputStream("/Users/luozijian/Downloads/mygitsource/demo/src/test/java/com/example/demo/tempFile");
            System.out.println(is.getFD().valid());
            new FileDescriptor();
        }

        @org.junit.Test
        public void testString(){

            String a = "abc";

            String b = a;
            a = a.substring(0,1);
            System.out.println(b);

            System.out.println(3 << 0); //3*(2^0) = 3*1 = 3


        }


    @org.junit.Test
    public void testSystem(){

        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.version"));

        System.setProperty("ZSH", "ZSH");
        System.out.println(System.getProperty("ZSH"));

        System.out.println(System.getProperty("test"));


        HashMap<String, String> map = Maps.newHashMap();
        String a = map.put("1", "a");
        a = map.put("1", "b");
        System.out.println(a);

        System.out.println(Objects.equals(null, null));


    }

    @org.junit.Test
    public void testListAdd(){

        List<List<OrderId>> orderResultList = Lists.newArrayList();



        List<List<OrderId>> orderList = Lists.newArrayList();

        orderList.add(Lists.newArrayList(OrderId.builder().orderId("1").type("CRC").build(),
                OrderId.builder().orderId("2").type("CRC").build()));

        orderList.add(Lists.newArrayList(OrderId.builder().orderId("3").type("CRC").build(),
                OrderId.builder().orderId("4").type("CRC").build()));

        orderList.add(Lists.newArrayList(OrderId.builder().orderId("1").type("CRC").build(),
                OrderId.builder().orderId("5").type("CRC").build()));

        orderList.add(Lists.newArrayList());
        orderList.add(Lists.newArrayList());

        orderList.stream().forEach(list->{

            if(CollectionUtils.isEmpty(orderResultList)){
                orderResultList.add(list);
            }else {

                boolean isDup = false;
                for(int i = 0; i < orderResultList.size(); i++){

                    Collection retainList = ListUtils.retainAll(orderResultList.get(i), list);
                    if(!CollectionUtils.isEmpty(retainList)){
                        isDup = true;
                        break;
                    }

                }

                if(!isDup){
                    orderResultList.add(list);
                }
            }

        });


        System.out.println(orderResultList.size());


        OrderId orderId1 = OrderId.builder().orderId("1").type("CRC").build();
        OrderId orderId2 = OrderId.builder().orderId("1").type("CRC").build();

        System.out.println(orderId1 == orderId2);
        System.out.println(orderId1.equals(orderId2));


    }


    @org.junit.Test
    public void test3(){

        String s = "41";

        char[] chars = s.toCharArray();
        int res = 0, temp = 0;
        for(int i = 0; i < chars.length; i++){

            temp = res;
            int num = chars[i] - '0';
            res = res * 10 + num;

            if(res/10 != temp){
                System.out.println("溢出了");
            }

        }

        System.out.println(res);


    }


    @org.junit.Test
    public void testMid(){
        int mid = (6 + 9) >> 1;
        System.out.println(mid);
    }


    @org.junit.Test
    public void testList1(){
        List<Integer> list = new ArrayList<>(10);
        System.out.println(list.get(3));

    }


    @org.junit.Test
    public void test20(){
        String str = "abc\nd";

        System.out.println(str.length());
        System.out.println(str);

        System.out.println(22 >> 1);

        int sc = 0;
        int n = (sc > 0) ? sc : 16;

        sc = n - (n >>> 2);
        System.out.println(sc);

    }


    @org.junit.Test
    public void test21(){

//        String url = "http://baidu.com?a=%s&b=%s";
//        System.out.println(String.format(url, "test"));

//        String url = "http://baidu.com?a={0}&b={1}";
//        System.out.println(MessageFormat.format(url, "test"));


        String a = "{\n" +
                "\"acceptNumber\": \"007302f7d3c71986\",\n" +
                "\"agentType\": \"21\",\n" +
                "\"businessId\": \"K2021092910005\",\n" +
                "\"callPhone\": \"15989129220\",\n" +
                "\"callType\": \"inbound\",\n" +
                "\"createTime\": 1632904041701,\n" +
                "\"endTime\": 1632906203560,\n" +
                "\"firstQueue\": \"å\\u009b½é\\u0099\\u0085ç§\\u009fè½¦T1\",\n" +
                "\"isChoiceKeyword\": 0,\n" +
                "\"isNew\": 0,\n" +
                "\"isTalkOverTime\": 0,\n" +
                "\"isUrgent\": 0,\n" +
                "\"ivrMenu\": \"å\\u009b½é\\u0099\\u0085ç§\\u009fè½¦-ç§\\u009fè½¦é¢\\u0084è®¢å\\u008f\\u008aç\\u0094¨è½¦å\\u0089\\u008då\\u0092¨è¯¢\",\n" +
                "\"kfEndTime\": 0,\n" +
                "\"kfJoinTime\": 0,\n" +
                "\"lastKfMsgTime\": 0,\n" +
                "\"lastQueue\": \"å\\u009b½é\\u0099\\u0085ç§\\u009fè½¦T1\",\n" +
                "\"lastUserMsgTime\": 0,\n" +
                "\"online\": 0,\n" +
                "\"oversea\": 0,\n" +
                "\"phoneCode\": \"86\",\n" +
                "\"phoneLocation\": \"å¹¿ä¸\\u009c å¹¿å·\\u009e\",\n" +
                "\"project\": \"phone\",\n" +
                "\"serverTime\": 0,\n" +
                "\"serviceSource\": \"zzc\",\n" +
                "\"sortTime\": 1632904041701,\n" +
                "\"terminalId\": 0,\n" +
                "\"unreadCount\": 0,\n" +
                "\"website\": \"zzc\"\n" +
                "}";
        System.out.println(a.length());

    }



    @org.junit.Test
    public void test22(){
        List<String> list1 = Lists.newArrayList("1", "2", "3");
        List<String> list2 = Lists.newArrayList();

        String temp;
        for(String a : list1){
            temp = a;
            list2.add(temp);
        }

        System.out.println(list2);

    }


    @org.junit.Test
    public void test23(){

        Map<String, String> map = new HashMap<>();
        map.put("a", "a11");

        List<String> username = new ArrayList();
        username.add("a");
//        username.add("b");

        List<String> list = username.stream()
                .map(name->{
                    if(map.get(name) != null){
                        return map.get(name);
                    }
                    return name;
                }).collect(Collectors.toList());

        System.out.println(list);

    }

    @org.junit.Test
    public void test24(){
        List all = Lists.newArrayList();
        for (int i = 1; i <= 99; i++){
            all.add(i);
        }

        List batch = Lists.newArrayList();

        all.stream().forEach(e->{
            batch.add(e);

            if(batch.size() >= 20){
                System.out.println(batch);
                batch.clear();
            }
        });

        if(!CollectionUtils.isEmpty(batch)){
            System.out.println(batch);
        }

    }

    @org.junit.Test
    public void test25(){
        testSwitch(null);
    }

    private void testSwitch(String param){

        //switch括号里的参数不能为null
        switch (param){

        }

    }

    @org.junit.Test
    public void test51(){

        LocalDate now = LocalDate.now();
        System.out.println(now.toString());
        System.out.println("计算两个时间的差：");
        LocalDate end = LocalDate.parse("2022-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        long days = now.toEpochDay()-end.toEpochDay();
        System.out.println("days= " + days);
        System.out.println(now.toEpochDay());

        System.out.println(end.compareTo(now));



        System.out.println("-------------");

        LocalDateTime monthStartTime = LocalDateTime.of(2022, 4, 1, 0, 0, 0);

        Date date = new Date(monthStartTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(DateUtil.beginOfMonth(date));
        System.out.println(DateUtil.endOfMonth(date));


    }

    public static String getDateLastDay(int year, int month) {

        //year="2018" month="2"
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);


        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(calendar.getTime());
    }

    @org.junit.Test
    public void test33(){
        String str = LocalDate.now().atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(str);

        String str1 = LocalDate.now().plusMonths(3L).atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(str1);
    }

    @org.junit.Test
    public void test32() {
        LocalDate now = LocalDate.now();
        LocalDateTime startTime = now.atStartOfDay();
        System.out.println(startTime);
        LocalDateTime endTime = now.plusYears(2L).plusDays(1L).atStartOfDay();

        System.out.println(endTime);

        Collections.EMPTY_LIST.stream().collect(Collectors.toList());

//        Integer a = 1;
//        Integer b = 2;
//        boolean isEqual = Objects.equals(a, b);

        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("1.0");

        List<BigDecimal> list = Lists.newArrayList(a, b);

        String str = list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        System.out.println(str);

        System.out.println(Lists.newArrayList(a, b));

        System.out.println(null instanceof List);

        OrderId orderId = new OrderId();
        orderId.setOrderId("1");

        System.out.println(orderId);

//        System.out.println(new StringBuilder(null));

        System.out.println(LocalDate.now().atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }




}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class OrderId{
    private String orderId;
    private String type;
    
    private Boolean save = orderId == null;

    @PostConstruct
    public void init(int a){
        System.out.println("PostConstruct");
    }
}

