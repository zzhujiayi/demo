package com.example.demo.zookeeper.lock;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.locks.LockSupport;

public class MyLock {
    private ZooKeeper zooKeeper;

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private String path = "/mylock";
    private String data = "1";

    private Thread thread;

    private String lockName;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public String getLockName() {
        return lockName;
    }

    public void setLockName(String lockName) {
        this.lockName = lockName;
    }

    public void lock() {
        setThread(Thread.currentThread());

        zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, callback, this);
        LockSupport.park();
    }

    public void unlock() {
        try {
            System.out.println(thread.getName() + " release lock " + getLockName());
            zooKeeper.delete(getLockName(), -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }
}
