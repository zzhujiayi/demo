package com.example.demo.zookeeper.config;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

public class ConfigWatch implements Watcher {

    private ConfigUtils configUtils;

    public void setConfigUtils(ConfigUtils configUtils) {
        this.configUtils = configUtils;
    }

    private ZooKeeper zooKeeper;

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public void init(){
        updateConfig();
    }

    @Override
    public void process(WatchedEvent event) {
        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                updateConfig();
                break;
            case NodeDeleted:
                configUtils.setConf(null);
                break;
            case NodeDataChanged:
                updateConfig();
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

        try {
            zooKeeper.exists(path, this);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String path = "/config";

    private void updateConfig() {
        zooKeeper.getData(path, this, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                if (stat != null) {
                    String config = new String(data);
                    configUtils.setConf(config);
                }
            }
        }, null);
    }
}
