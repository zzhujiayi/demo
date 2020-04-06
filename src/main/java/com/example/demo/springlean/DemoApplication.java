package com.example.demo.springlean;

import org.apache.catalina.Executor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sun.misc.Unsafe;

import javax.swing.plaf.TableHeaderUI;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

@SpringBootApplication
public class DemoApplication {

    private static boolean status;

    //static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(DemoApplication.class);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> {
            return 1;
        });
        System.in.read();

        ClassLoader classLoader = String.class.getClassLoader();



    }


    private static int getIntValue() {
        return 1;
    }

    private Unsafe getUnsafe() throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        Unsafe.getUnsafe();
        return unsafe;
    }

}