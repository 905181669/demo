package com.example.demo.rest;

import org.springframework.http.CacheControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author: luozijian
 * @date: 8/7/21 16:31:40
 * @description:
 */
@Controller
@RequestMapping("/http")
public class CacheControlController {


    /**
     * 缓存与用户行为有关，当直接输入网址回车时不会用缓存，当点击a标签或ajax时才会生效
     * @param response
     * @return
     */
    @RequestMapping("/cache-control/max-age")
    public ModelAndView maxAge(HttpServletResponse response) {
        System.out.println("服务端访问时间：" + new Date());
        String headerValue = CacheControl.maxAge(30, TimeUnit.SECONDS).getHeaderValue();
        response.addHeader("Cache-Control", headerValue);
        ModelAndView mv = new ModelAndView("maxAge");
        return mv;
    }
}

