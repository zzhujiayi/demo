package com.example.demo.juc;

import sun.reflect.Reflection;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Application {
    private static void test(){
        Class<?> caller = Reflection.getCallerClass();
        int i=0;
    }
    public static void main(String[] args) {


        test();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1", "1");
        concurrentHashMap.remove("1");


        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "1");

        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("123");
        System.out.println(threadLocal.get());

        threadLocal = null;
        System.gc();

        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        threadLocal1.set("234");
        System.out.println(threadLocal1.get());

    }
}
