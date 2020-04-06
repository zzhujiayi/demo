package com.example.demo.zookeeper;

import com.example.demo.zookeeper.config.ConfigUtils;
import com.example.demo.zookeeper.config.ConfigWatch;
import com.example.demo.zookeeper.config.DefaultWatch;
import com.example.demo.zookeeper.lock.Callback;
import com.example.demo.zookeeper.lock.MyLock;
import org.apache.zookeeper.ZooKeeper;

public class Application {
    private static ZooKeeper zk;

    public static void main(String[] args) throws Exception {
        DefaultWatch defaultWatch = new DefaultWatch();
        zk = new ZooKeeper("172.17.96.99:2181/locks", 10000, defaultWatch);
        testDistLock();
    }

    private static void testConfig() {
        ConfigUtils configUtils = new ConfigUtils();
        ConfigWatch configWatch = new ConfigWatch();
        configWatch.setConfigUtils(configUtils);
        configWatch.setZooKeeper(zk);
        configWatch.init();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(configUtils.getConf());
        }
    }

    private static void testDistLock() {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                MyLock myLock = new MyLock();
                myLock.setZooKeeper(zk);
                Callback callback=new Callback();
                callback.setZooKeeper(zk);
                myLock.setCallback(callback);

                myLock.lock();

//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                myLock.unlock();
            });

            thread.start();
        }
    }


}
