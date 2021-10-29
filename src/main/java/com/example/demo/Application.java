package com.example.demo;


import com.example.demo.spring.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Application {
    public static void main(String[] args) {
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal1 = new InheritableThreadLocal<>();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("thread local abc");
        inheritableThreadLocal.set("abc");
        System.out.println(inheritableThreadLocal.get());
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(inheritableThreadLocal.get());
            System.out.println("inh th l 1 : "+ inheritableThreadLocal1.get());
        });

        thread.start();

        inheritableThreadLocal1.set("inh threa loca 1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        inheritableThreadLocal.set("123");
        System.out.println(inheritableThreadLocal.get());


        SynchronousQueue<String> synchronousQueue=new SynchronousQueue<>();
        synchronousQueue.offer("");


        A a = new A();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(100);
        concurrentHashMap.put(1, 1);

        HashMap<String, String> hashMap = new HashMap<>(100);
        hashMap.put("", "");

        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(1);
        final Method[] methods = A.class.getMethods();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 10, TimeUnit.HOURS, linkedBlockingQueue, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.execute(()->{});

        AbstractQueuedSynchronizer abstractQueuedSynchronizer;

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });


        //linkedBlockingQueue.take()

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);


        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        reentrantReadWriteLock.readLock().lock();
        reentrantReadWriteLock.writeLock().lock();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<String> forkJoinTask = new ForkJoinTask<String>() {
            @Override
            public String getRawResult() {

                return null;
            }

            @Override
            protected void setRawResult(String value) {

            }

            @Override
            protected boolean exec() {
                return false;
            }
        };

        forkJoinTask.fork();

    }

    static class A implements Interface1 {
        private Integer b;

        public Integer getB() {
            return b;
        }

        public void setB(Integer b) {
            this.b = b;
        }

        @Override
        public void doWork() {
            System.out.println("do");
        }
    }

    interface Interface1 {
        @Log
        @Deprecated
        void doWork();
    }
}