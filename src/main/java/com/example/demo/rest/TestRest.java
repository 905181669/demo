package com.example.demo.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author: luozijian
 * @date: 2020-05-02 21:58:49
 * @description:
 */

@Controller
public class TestRest {


    @GetMapping("/toUpload")
    public String toUpload(HttpServletRequest request){

        return "toUpload";
    }


}
