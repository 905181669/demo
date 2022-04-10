package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: luozijian
 * @date: 4/29/21 17:04:58
 * @description:
 */
@Slf4j
@Controller
public class TemplateController {

    @Autowired
    TaskExecutor taskExecutor;

    @RequestMapping("/toUpload")
    public ModelAndView toUpload(){

        ModelAndView mv = new ModelAndView("toUpload");

        return mv;
    }

    @RequestMapping("/thleaf")
    public String thleaf(){

        return "thleaf";
    }

    @RequestMapping("/websocket")
    public String websocket(){

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                log.info("111");
            }
        });
        return "websocket";
    }


    @RequestMapping("/cacheControl")
    public ModelAndView cacheControl(){

        ModelAndView mv = new ModelAndView("cacheControl");

        return mv;
    }


}