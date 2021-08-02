package com.example.demo;


import com.example.demo.springlean.Log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Application {
    public static void main(String[] args) {
        A a = new A();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(100);
        concurrentHashMap.put(1, 1);

        HashMap<String, String> hashMap = new HashMap<>(100);
        hashMap.put("","");
        final Method[] methods = A.class.getMethods();
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 10, TimeUnit.HOURS, linkedBlockingQueue, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        AbstractQueuedSynchronizer abstractQueuedSynchronizer;

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(4, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        });


        LinkedBlockingDeque linkedBlockingDeque = null;

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("");
        threadLocal.get();

        ReentrantLock reentrantLock = null;
        reentrantLock.lock();
        reentrantLock.unlock();
        //reentrantLock.lockInterruptibly();

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        reentrantReadWriteLock.readLock().lock();
        reentrantReadWriteLock.writeLock().lock();
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