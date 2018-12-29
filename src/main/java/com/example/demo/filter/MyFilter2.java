package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFilter2 implements Filter{

	@Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub
    }
	
	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
        System.out.println("this is MyFilter2,url :"+request.getRequestURI());
        filterChain.doFilter(srequest, sresponse);
		
	}
	
	@Override
    public void destroy() {
        // TODO Auto-generated method stub
    }

}
