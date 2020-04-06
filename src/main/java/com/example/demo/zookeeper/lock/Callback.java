package com.example.demo.zookeeper.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class Callback implements AsyncCallback.StringCallback {

    private ZooKeeper zooKeeper;

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }


    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        try {

            MyLock myLock = (MyLock) ctx;
            myLock.setLockName(name);

            LockWatcher lockWatcher = new LockWatcher();
            lockWatcher.setThread(myLock.getThread());

            List<String> children = zooKeeper.getChildren("/", false);
            Collections.sort(children);
            for (String s : children) {
                System.out.println(s);
            }

            int idx = children.indexOf(name.substring(1));
            if (idx == 0) {
                //get lock
                LockSupport.unpark(myLock.getThread());
                System.out.println(myLock.getThread().getName() + " get lock " + name);
            } else {
                //watch the prev node
                Stat stat = zooKeeper.exists("/" + children.get(idx - 1), new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        switch (event.getType()) {
                            case None:
                                break;
                            case NodeCreated:
                                break;
                            case NodeDeleted:
                                System.out.println(myLock.getThread().getName() + " get lock " + name);
                                LockSupport.unpark(myLock.getThread());
                                break;
                            case NodeDataChanged:
                                break;
                            case NodeChildrenChanged:
                                break;
                            case DataWatchRemoved:
                                break;
                            case ChildWatchRemoved:
                                break;
                            case PersistentWatchRemoved:
                                break;
                        }
                    }
                });
                if (stat == null) {
                    System.out.println(myLock.getThread().getName() + " get lock " + name);
                    LockSupport.unpark(myLock.getThread());
                }
//                zooKeeper.exists("/" + children.get(idx - 1), lockWatcher, new StatCallback() {
//                    @Override
//                    public void processResult(int rc, String path, Object ctx, Stat stat) {
//                        //if (stat == null) {
//                        //LockSupport.unpark(myLock.getThread());
//                        //}
//                    }
//                }, null);
            }

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
