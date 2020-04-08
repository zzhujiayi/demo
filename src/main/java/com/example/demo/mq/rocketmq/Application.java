package com.example.demo.mq.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class Application {
    public static void main(String[] args) throws RemotingException, MQClientException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("");

        Message msg = new Message();


        producer.send(msg);
        producer.sendOneway(msg);
        producer.send(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
consumer.setNamesrvAddr("");
        consumer.suspend();
        consumer.shutdown();
        consumer.start();
        consumer.setConsumeMessageBatchMaxSize(3);
        consumer.getConsumeMessageBatchMaxSize();
        consumer.setNamesrvAddr("");
        consumer.subscribe("", "");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                return null;
            }
        });

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                return null;
            }
        });
    }
}
