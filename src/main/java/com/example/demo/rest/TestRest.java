package com.example.demo.rest;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.TestLockEntity;
import com.example.demo.rest.req.Phone;
import com.example.demo.rest.req.SendInboundCallParam;
import com.example.demo.rest.resp.ShangriResult;
import com.example.demo.service.TestLockService;
import com.example.demo.service.TestService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.HostInfoEnvironmentPostProcessor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: luozijian
 * @date: 2020-05-02 21:58:49
 * @description:
 */
@Slf4j
@Import(TestA.class)
@RestController
public class TestRest {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    TestA testA;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private TestService testService;



    public static AtomicInteger ctl = new AtomicInteger(0);


    @GetMapping("/getEurekaIntance")
    public String getEurekaIntance(HttpServletRequest request){
        List<InstanceInfo> list = DiscoveryManager.getInstance().getEurekaClient().getApplication("demo-service").getInstances();

        List<ServiceInstance> list1 = discoveryClient.getInstances("demo-service");

        return list1.toString();
    }


    @GetMapping("/unregister")
    public String unregister(){

        DiscoveryManager.getInstance().shutdownComponent();
        return "反注册成功";
    }


    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        Enumeration enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            System.out.println(enumeration.nextElement() + "-->" + request.getHeader(enumeration.nextElement().toString()));
        }


        return "上传成功: " + file.getOriginalFilename() + "；大小：" + file.getSize()/1024 + "kb";
    }


    @RequestMapping("/echo")
    public String echo(String content, HttpServletRequest request) throws Exception{

//        Thread.sleep(5000);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String header = headerNames.nextElement();
            System.out.println(header + "--->" + request.getHeader(header));
        }


//        log.info(request.getServletContext().getAttribute("luozijian").toString());
        log.info(Thread.currentThread().getName());
        return "echo: " + content + "-" + System.currentTimeMillis();
    }



    @RequestMapping("/longTime")
    public String longTime() throws Exception{
//        Thread.sleep(1000);
//        testService.test();

        return "我是耗时5秒的请求";
    }

    /**
     * -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=64m -Xms64m -Xmx64m -Xss256k -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -XX:+PrintCommandLineFlags
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/shortTime")
    public String shortTime(HttpServletRequest request, HttpServletResponse response){

        //Content-Length: 5（汉字：3字节，数字和英文字符占1字节）

//        try{
//            Thread.sleep(200);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        return "cat";
    }



    @RequestMapping("/content")
    public String content(String name){

        //Content-Length: 5（汉字：3字节，数字和英文字符占1字节）
        return name;
    }





    @RequestMapping("/redirect")
    public void  redirect(HttpServletResponse response) throws Exception{

        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", "echo");

//        response.sendRedirect("echo");
    }

    @GetMapping("/pojoTest")
    public String pojoTest(Phone phone){

        System.out.println(phone);
        return JSON.toJSONString(phone);
    }

    @GetMapping("/testDecimal")
    public BigDecimal testDecimal(BigDecimal value){
        return value;
    }
    /**
     * {
     * "status": 0, // 处理结果
     * "message":"success"
     * "data":{
     *        "serialNo":"请求带的流水号"
     *    }
     * }
     * @param
     * @param req
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/coral/call/incoming/gz")
    public String incomingCall(HttpServletRequest request, @RequestBody SendInboundCallParam req) throws Exception{

        System.out.println(request.getHeader("Authorization"));


        int num = ctl.getAndIncrement();
        //控制单数成功
        int status = num % 2 == 0 ? -2 : 0;

//        int sleep = num % 2 == 0 ? 15000 : 1000;

        ShangriResult result = ShangriResult
                .builder()
                .message("success")
                .status(0)
                .data(req.getSerialNo())
                .build();

        //测试网络延迟
        Thread.sleep(2000);

        return JSON.toJSONString(result) ;

    }







    @Bean
    public TestD getTestD(TestC testC){
        return new TestD();
    }

}



class TestA implements ApplicationContextAware{

    public ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}


@Component
class TestC implements Lifecycle {

    @Override
    public void start() {
        System.out.println("start...");
    }

    @Override
    public void stop() {
        System.out.println("stop...");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}


class TestD{

}