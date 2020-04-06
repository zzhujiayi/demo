package com.example.demo.activemq;

import javax.jms.JMSException;
import javax.jms.QueueRequestor;

public class Application {
    public static void main(String[] args) {
        QueueRequestor queueRequestor=null;
        try {
            queueRequestor.request(null);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
