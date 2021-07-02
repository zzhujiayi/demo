package com.example.demo.springlean.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashSet;
import java.util.Set;

//@Log
@RequestMapping("/controller/")
@RestController
public class MyController {

    private Set<DeferredResult<String>> set = new HashSet<>();

    @GetMapping("getConfig")
    public DeferredResult<String> getConfig() {
        DeferredResult<String> result = new DeferredResult<>(10000L, "no update");

        result.onCompletion(() -> {
            set.remove(result);
        });

        result.onTimeout(() -> {
            set.remove(result);
        });

        set.add(result);

        return result;
    }

    @GetMapping("setConfig")
    public void setConfig(String config) {
        set.forEach(d -> {
            d.setResult(config);
        });
    }
}
