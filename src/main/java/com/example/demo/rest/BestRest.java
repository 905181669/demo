package com.example.demo.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Enumeration;

/**
 * @author: luozijian
 * @date: 2020-05-02 21:58:49
 * @description:
 */

@RestController
@RequestMapping("")
public class BestRest {

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件，返回文件的链接和附件id", notes = "上传文件")
    public String upload(@RequestParam("file") MultipartFile file, String type) {

        System.out.println(file.getOriginalFilename());
        return "上传成功";
    }


    @GetMapping("/test")
    public String test() throws Exception{
        Thread.sleep(1000);
        return Instant.now().getEpochSecond()+"";
    }
}
