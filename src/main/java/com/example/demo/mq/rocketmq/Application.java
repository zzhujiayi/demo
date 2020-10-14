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
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception, RemotingException, MQClientException, InterruptedException, MQBrokerException {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("group1");
            producer.setNamesrvAddr("http://172.18.127.4:9876");
            producer.start();
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    "Hello RocketMQ".getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );


            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
            producer.shutdown();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

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
