package com.example.demo.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.*;

public class Application {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
        //factory.setHost(hostName);
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume("queue3", false, new Consumer() {
            @Override
            public void handleConsumeOk(String consumerTag) {

            }

            @Override
            public void handleCancelOk(String consumerTag) {

            }

            @Override
            public void handleCancel(String consumerTag) throws IOException {

            }

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

            }

            @Override
            public void handleRecoverOk(String consumerTag) {

            }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                int i = 0;
            }
        });

        channel.basicPublish("test", "", null, "test".getBytes());
        channel.basicPublish("test1", "q", null, "test1 q".getBytes());
        channel.basicPublish("test1", "u", null, "test1 u".getBytes());
    }
}
