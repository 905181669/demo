package com.example.hystrix;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.apache.commons.configuration.AbstractConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: luozijian
 * @date: 9/5/21 15:11:34
 * @description:
 */
public class HystrixCommandTest1 {

    private static final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

    // http请求超时时间
    private static final int HTTP_TIMEOUT = 10000;
    // hystrix超时时间
    private static final int HYSTRIX_TIMEOUT = 1000;

    private RestTemplate restTemplate;

    @Before
    public void init() {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(HTTP_TIMEOUT);
        restTemplate = new RestTemplate(httpRequestFactory);
    }

    @Test
    public void test() {
        // 创建HystrixCommand.Setter
        HystrixCommandProperties.Setter propSetter = HystrixCommandProperties.Setter()
                .withExecutionTimeoutEnabled(true)	//开启超时机制
                .withExecutionTimeoutInMilliseconds(HYSTRIX_TIMEOUT)	//设置超时时间
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)	//线程隔离
                .withExecutionIsolationThreadInterruptOnTimeout(true);	//这里设置超时中断线程，但其实没有实际效果
        HystrixCommand.Setter setter = HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("queryData"))
                .andCommandPropertiesDefaults(propSetter);

        // 通过Setter创建创建HystrixCommand
        HystrixCommand<String> hystrixCommand = new HystrixCommand<String>(setter) {
            @Override
            protected String run() throws Exception {
                // 发起http请求
                print("send request");
                String result = restTemplate.getForObject("http://127.0.0.1:9090/longTime", String.class);
                print("get response");
                return result;
            }

            @Override
            protected String getFallback() {
                print("fallback");
                return null;
            }
        };
        print("execute command");
        // 执行HystrixCommand
        String result = hystrixCommand.execute();
//        hystrixCommand.queue();
        print("get result=" + result);
        // 阻塞main线程，防止程序终止

    }

    private void print(String msg) {
        System.out.println(df.format(new Date()) + " [" + Thread.currentThread().getName() + "]：" + msg);
    }
}
