package com.example.demo.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: luozijian
 * @date: 2020-10-31 11:36:01
 * @description:
 */

@FeignClient(name = "eureka-service", fallback = EurekaRemoteFallback.class)
public interface EurekaRemote {

    @GetMapping("/test")
    String test();

    @GetMapping("/testHystrix")
    String testHystrix(@RequestParam(value = "name") String name);
}

@Component
class EurekaRemoteFallback implements EurekaRemote{
    @Override
    public String test() {
        return null;
    }

    @Override
    public String testHystrix(String name) {
        return "the user does not exit in this system, please comfirm it";
    }
}