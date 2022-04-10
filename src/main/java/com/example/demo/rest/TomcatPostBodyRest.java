package com.example.demo.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 8/11/21 09:49:52
 * @description:
 */


@RestController
@Slf4j
public class TomcatPostBodyRest {

    ExecutorService executorService = Executors.newFixedThreadPool(2);


    @PostMapping("/post")
    public String post(@RequestBody Body body){

        String content = body.content;
        System.out.println(content.length()/1024.0/1024.0);
        return "success";
    }

    @PostMapping("/form")
    public String form(Body body){

        String content = body.content;
        System.out.println(content.length()/1024.0/1024.0);
        return "success";
    }


    /**
     * 测试是否支持异步
     * @param time
     * @param response
     * @throws Exception
     */
    @GetMapping("/time")
    public void time(long time, HttpServletRequest request, HttpServletResponse response) throws Exception {

        executorService.submit(()->{

            try{
                TimeUnit.SECONDS.sleep(time);
                response.getOutputStream().write(String.valueOf(time).getBytes());
                response.getOutputStream().flush();
                return;
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        /**
         * servlet同步模式时，只有nio-9090-exec-1线程返回时才会把response中的数据返回给客户端；
         * 所以在servlet3.0未支持异步时，如果nio线程先返回了，就没有办法把response中的数据返回给客户端了；
         */
        TimeUnit.SECONDS.sleep(time * 2);


//        return time;
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
class Body{
    String content;
}