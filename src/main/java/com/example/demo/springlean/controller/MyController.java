package com.example.demo.springlean.controller;

import com.example.demo.springlean.Log;
import com.example.demo.springlean.Test;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class MyController {
    public MyController() {
        int i = 0;
    }

    @Autowired
    private Test test;
//
//    @Autowired
//    private TestB testB;

    @Transactional
    @GetMapping("/say")
    public String say() {
        com.netflix.hystrix.HystrixCommand hystrixCommand=null;
        hystrixCommand.execute();
        hystrixCommand.queue();
        return "hello world!";
    }
}
