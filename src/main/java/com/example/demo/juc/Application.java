package com.example.demo.juc;

public class Application {



    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("123");
        System.out.println(threadLocal.get());

        threadLocal=null;
        System.gc();

                ThreadLocal<String> threadLocal1=new ThreadLocal<>();
        threadLocal1.set("234");
        System.out.println(threadLocal1.get());

    }
}
