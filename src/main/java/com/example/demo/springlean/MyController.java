package com.example.demo.springlean;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/say")
    public String say() {
        com.netflix.hystrix.HystrixCommand hystrixCommand=null;
        hystrixCommand.execute();
        hystrixCommand.queue();
        return "hello world!";
    }
}
