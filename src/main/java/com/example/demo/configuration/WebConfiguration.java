package com.example.demo.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.MyFilter;
import com.example.demo.filter.MyFilter2;

@Configuration
public class WebConfiguration {
	
	@Bean
	public FilterRegistrationBean testFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.setName("MyFilter");
        registration.setOrder(6); //数值越小，越优先执行
        return registration;
	}
	
	@Bean
	public FilterRegistrationBean test2FilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter2());
        registration.addUrlPatterns("/*");
        registration.setName("MyFilter2");
        registration.setOrder(1);
        return registration;
	}
}
