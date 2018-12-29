package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;

@Controller
public class WelcomeController {

	

	@GetMapping("/")
    public String welcome(Map<String, Object> map,ModelMap model) {
        map.put("time", new Date());
        map.put("message", "hello world");
        model.addAttribute("name", "罗子健");
        return "welcome";
    }
	
	@RequestMapping("/thleaf")
    public String index(ModelMap map,HttpServletRequest request) {
        map.addAttribute("message", "http://www.ityouknow.com");
        
        request.getSession().setAttribute("name", "tom");
        
        map.addAttribute("userName", "neo");
        map.addAttribute("users", getUserList());
        map.addAttribute("count", 12);
        map.addAttribute("date", new Date());
        return "thleaf";
    }
	
	public List<User> getUserList(){
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
	
}
