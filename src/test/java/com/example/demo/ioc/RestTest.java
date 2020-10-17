package com.example.demo.ioc;

import com.example.demo.rest.BestRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author: luozijian
 * @date: 2020-09-13 11:07:08
 * @description:
 */

@RunWith(SpringRunner.class)
@WebMvcTest()
//@SpringBootTest()
//@AutoConfigureMockMvc
public class RestTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testExample() throws Exception {
        //groupManager访问路径
        //param传入参数
        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/test").param("pageNum","1").param("pageSize","10")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        System.out.println(content);

    }
}
