package com.example.demo.reactiv.rx;

import com.alibaba.fastjson.TypeReference;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.HashMap;

public class Application {
    public static void main(String[] args) {
//        Flowable.just("hello world").subscribe(System.out::println);
//
//        Flowable.range(1, 10)
//                .observeOn(Schedulers.computation())
//                .map(v -> v * v)
//                .blockingSubscribe(System.out::println);
//
//        TypeReference<String> t;

        Object obj = 1;
        sss(1);
    }

    public static <T> void sss(T t) {
        Class cls= t.getClass();
        System.out.println(t.getClass());
    }
}
