package com.example.demo.springlean.controller;

import com.example.demo.springlean.Log;
import com.example.demo.springlean.Model;
import com.example.demo.springlean.Test;
import org.apache.kafka.clients.producer.MockProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return "hello world!";
    }

    @PostMapping("/ss")
    public String ss(@RequestBody @Validated Model model) {
        return "OK";
    }
}
