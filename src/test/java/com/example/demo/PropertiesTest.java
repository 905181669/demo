package com.example.demo;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

	@Value("${neo.title}")
	private String title;
	
	@Resource
	private NeoProperties properties;
	
	@Resource
	private OtherProperties oProperties;
	
	
	@Test
	public void testSingle() {
		Assert.assertEquals(title,"纯洁的微笑");
		System.out.println("title:"+title);
	}
	
	@Test
	public void testMore() throws Exception {
	    System.out.println("title:"+properties.getTitle());
	    System.out.println("description:"+properties.getDescription());
	}
	
	@Test
	public void testOtherProperties() {
		 System.out.println("title:"+oProperties.getTitle());
		    System.out.println("description:"+oProperties.getDescription());
	}
	
	
	
	
	
	
}
