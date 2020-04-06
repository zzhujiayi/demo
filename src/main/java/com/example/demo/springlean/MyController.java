package com.example.demo.springlean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class MyController {
    public MyController() {
        int i = 0;
    }

//    @Autowired
//    private Test test;
//
//    @Autowired
//    private TestB testB;

    @GetMapping("/say")
    public String say() {
        return "hello world!";
    }
}
