package com.example.hystrix;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Future;

/**
 * @author: luozijian
 * @date: 4/19/21 17:57:58
 * @description:
 */
@Slf4j
public class HystrixCommandTest {


    @Test
    public void test1() throws Exception{

//        String url = "http://localhost:9090/longTime";
//        String url = "http://localhost:9090/shortTime";
        String url = "https://www.baidu.com";

//            String url = "http://localhost:9090/errorHello";


        HystrixCommandProperties.Setter propSetter = HystrixCommandProperties.Setter()
                .withCircuitBreakerErrorThresholdPercentage(50) // 默认50
                .withCircuitBreakerRequestVolumeThreshold(20) //默认20
                .withCircuitBreakerForceOpen(true)
                .withExecutionTimeoutEnabled(true)	//开启超时机制
                .withExecutionTimeoutInMilliseconds(5000)	//设置超时时间
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)	//线程隔离
                .withExecutionIsolationThreadInterruptOnTimeout(true);	//这里设置超时中断线程，但其实没有实际效果

        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("queryData"))
                .andCommandPropertiesDefaults(propSetter);

        for(int i = 0; i < 10; i++){
            HelloCommand helloCommand = new HelloCommand(setter);
            helloCommand.setUrl(url);
//            String result = helloCommand.execute();
            Future future = helloCommand.queue();
            System.out.println(future.isDone());

        }

    }


    @Setter
    public static class HelloCommand extends HystrixCommand<String>{

        private String url;

        protected HelloCommand(HystrixCommand.Setter setter) {
            super(setter);
        }

        @Override
        protected String run() throws Exception {

            //hystrix-TestGroup-1
            log.info(Thread.currentThread().getName());

            CloseableHttpClient httpClient = HttpClients.createDefault();
            for(int i = 0; i < 1; i++){
                /**
                 * 当服务端设置为Connection: keepAlive时，同一个httpClient发送多次http请求会复用tcp连接
                 * 不同的httpClient对象发起的http请求是不会复用的
                 */
                HttpGet httpGet = new HttpGet(url);
                //同步执行
                HttpResponse httpResponse = httpClient.execute(httpGet);
                String result = EntityUtils.toString(httpResponse.getEntity());
                System.out.println(result);
            }

            return "";
        }

        @Override
        protected String getFallback() {
            //HystrixObservableTimeoutOperator
            //HystrixTimer-1
            /**
             * 现在可以认为有两个线程，一个是hystrixCommand任务执行线程，
             * 一个是等着给hystrixCommand判定超时的线程，
             * 现在两个线程看谁能先把hystrixCommand的状态置换，
             * 只要任何一个线程对hystrixCommand打上标就意味着超时判定结束。
             *
             * 作者：青芒v5
             * 链接：https://www.jianshu.com/p/60074fe1bd86
             */
            return Thread.currentThread().getName() + " No fallback available.";
        }

    }


    @Test
    public void test(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String input = scanner.next();
            input = input.replaceAll(" ", "-");
            Calendar calendar = Calendar.getInstance();
            java.text.SimpleDateFormat formt = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd");
            Date date = null;
            try {
                date = formt.parse(input);
                calendar.setTime(date);
                int dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) - 1);
                int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
                System.out.println(dayOfYear + " " + dayOfWeek);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }


    @Test
    public void test11(){
        String input = "2022 1 2";
        input = input.replaceAll(" ", "-");
        Calendar calendar = Calendar.getInstance();
        java.text.SimpleDateFormat formt = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        Date date = null;
        try {
            date = formt.parse(input);
            calendar.setTime(date);
            int dayOfWeek = (calendar.get(Calendar.DAY_OF_WEEK) - 1);
            int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            System.out.println(dayOfYear + " " + dayOfWeek);
            System.out.println("今天星期" + (calendar.get(Calendar.DAY_OF_WEEK) - 1));
            System.out.println("今天是" + calendar.get(Calendar.YEAR) + "年的第"
                    + calendar.get(Calendar.DAY_OF_YEAR) + "天");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}