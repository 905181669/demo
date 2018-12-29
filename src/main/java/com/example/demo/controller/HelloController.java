package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

import io.swagger.annotations.Api;

/**
 * 
 * @author admin
 *@RestController 的意思是 Controller 里面的方法都以 JSON 格式输出，
 *不需要有其他额外的配置；如果配置为 @Controller，代表输出内容到页面。
 */
@RestController
public class HelloController {

	/**
	 * @RequestMapping("/hello") 
	 * 提供路由信息，"/hello" 路径的 HTTP Request 都会被映射到 hello() 方法上进行处理。
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello(String name) {
		return "hello world " + name;
	}
	
	@RequestMapping(name="/getUser",method=RequestMethod.GET)
	public User getUser(User user) {
        return user;
	}
	
	@RequestMapping("/getUsers")
	public List<User> getUsers() {
	    List<User> users=new ArrayList<User>();
	    User user1=new User();
	    user1.setName("neo");
	    user1.setAge(30);
	    user1.setPass("neo123");
	    users.add(user1);
	    User user2=new User();
	    user2.setName("小明");
	    user2.setAge(12);
	    user2.setPass("123456");
	    users.add(user2);
	   return users;
	}
	
	
	@RequestMapping("/get/{name}")
	public String get(@PathVariable String name) {
		return name;
	}
	
	@RequestMapping("/saveUser")
	public void saveUser(@Valid User user,BindingResult result) {
		System.out.println("user:"+user);
		if(result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for(ObjectError error : list) {
				System.out.println(error.getCode()+"-"+error.getDefaultMessage());
			}
		}
	}
	
	
	
	
	
}
