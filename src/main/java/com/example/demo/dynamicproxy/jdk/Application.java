package com.example.demo.dynamicproxy.jdk;

import java.util.concurrent.ConcurrentHashMap;

public class Application {
    public static void main(String[] args) {
        TargetImpl targetImpl = new TargetImpl();
        Target target = ProxyTest.newProxyInstance(targetImpl);

        ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>();
        map.put("","");
        target.say();
    }
}
