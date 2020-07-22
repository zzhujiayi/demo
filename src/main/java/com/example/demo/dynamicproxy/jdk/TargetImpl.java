package com.example.demo.dynamicproxy.jdk;

public class TargetImpl implements Target {
    @Override
    public void say() {
        System.out.println("hello");
    }
}
