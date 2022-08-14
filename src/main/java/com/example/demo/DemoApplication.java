package com.example.demo;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PreDestroy;


/**
 * ServletWebServerApplicationContext重写了finishRefresh方法，这个方法会启动tomcat服务器
 */

//@EnableHystrix
@EnableAsync
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan
@MapperScan({"com.example.demo.mapper"})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * TomcatServletWebServerFactory() 方法主要是为了解决上传文件大于 10M 出现连接重置的问题，
	 * 此异常内容 GlobalException 也捕获不到。
	 * @return
	 */
//	@Bean
    public TomcatServletWebServerFactory tomcatEmbedded() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
            if ((connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>)) {
                /**
                 * ConnectionTimeout默认60，keepaliveTimeout默认和connectionTimeout一样
                 */

//                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setKeepAliveTimeout(10000);

                /**
                 * tomcat 维持连接支持重用，但会在下面两种情况下会关闭连接：
                 *
                 * 空闲超时关闭，默认20秒
                 * 重用次数达到限制时关闭 由maxKeepAliveRequests 参数控制，默认100
                 * maxKeepAliveRequests 参数如果你设置-1，那就是长连接了。否则，
                 * 一个连接只要发送了100次就会在响应头里设置Connection:close 告诉客户端，
                 * 我要关闭连接了，这也是为啥你用了连接池，还是不断新建连接的请求，在压测时特别明显。
                 *
                 */

                //关闭keepalive
//                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxKeepAliveRequests(1);
//                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
//                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxConnections(10);
//                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxThreads(5);


//                ((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSavePostSize(1);



            }
        });
        return tomcat;
    }


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
	    return new RestTemplate();
    }

    @Bean
    public IRule ribbonRule(){
	    return new RandomRule();
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("TerminalBean is destroyed");
    }
}

