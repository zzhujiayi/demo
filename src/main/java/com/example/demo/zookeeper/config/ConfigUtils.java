package com.example.demo.zookeeper.config;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.StringUtils;

import java.util.concurrent.CountDownLatch;

public class ConfigUtils {
    private String conf;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public String getConf() {
        if (StringUtils.isEmpty(this.conf)) {
            //block current thread
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this.conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
        if (StringUtils.isEmpty(conf)) {
            init();
        } else {
            countDownLatch.countDown();
        }
    }

    private void init() {
        countDownLatch = new CountDownLatch(1);
    }
}
