package com.example.demo.guava;

import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.RateLimiter;

import java.util.*;

/**
 * 限流
 */
public class Application {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(10);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", null);
        hashMap.put(null, "1");
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("1", null);
        hashtable.put(null, "1");

        Objects.hashCode(null);

        ArrayList<Integer> arrayList=new ArrayList<>();
        arrayList.add(0,1);

        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("123","123");
        linkedHashMap.forEach((k,v)->{});

        hashMap.forEach((k,v)->{});
    }
}
