package com.example.demo;



import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.hamcrest.Matchers;

import com.example.demo.controller.HelloController;

@SpringBootTest
public class HelloTest {

	private MockMvc mockMvc;
	@Test
	public void hello() {
		System.out.println("hello world");
	}
	
	/**
	 * @Before 注解的方法表示在测试启动的时候优先执行，一般用作资源初始化。
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}
	
	@Test
	public void getHello() throws Exception {
		/**
		 * 这种会打印很多东西出来
		 */
		mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=小明").
				accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
		
		/**
		mockMvc.perform(MockMvcRequestBuilders.post("/hello?name=小明")
	            .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print())
	            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("微笑")));
		*/
		
		
		
	}
	
}
