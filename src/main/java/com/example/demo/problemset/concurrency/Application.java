package com.example.demo.problemset.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Application {
    static class FooBar {
        private int n;

        public FooBar(int n) {
            this.n = n;
        }



        static CountDownLatch countDownLatch = new CountDownLatch(1);
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition1 = reentrantLock.newCondition();
        Condition condition2 = reentrantLock.newCondition();

        volatile boolean b = true;

        public void foo(Runnable printFoo) throws InterruptedException, BrokenBarrierException {
            countDownLatch.countDown();
            Semaphore semaphore=new Semaphore(1);
            semaphore.acquire();
            semaphore.release();
            for (int i = 0; i < n; i++) {

                // printFoo.run() outputs "foo". Do not change or remove this line.
                while (!b) {

                }
                printFoo.run();
                b = false;
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                while (b) {

                }

                printBar.run();
                b = true;

            }
        }
    }

    public static void main(String[] args) throws Exception {
        FooBar fooBar = new FooBar(10);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread1 = new Thread(() -> {
            try {
                fooBar.foo(() -> {
                    System.out.println("foo");
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        IllegalMonitorStateException illegalMonitorStateException;
        Thread thread2 = new Thread(() -> {
            try {
                fooBar.bar(() -> {
                    System.out.println("bar");
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        //Thread.sleep(100);
        thread2.start();

        //System.out.println("over.");

        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        concurrentHashMap.put("","");
    }
}
