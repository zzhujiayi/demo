package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.ScopeMetadata;

import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        kafkaProducer.send(new ProducerRecord<String, String>("my-topic", Integer.toString(1), Integer.toString(1)));

        kafkaProducer.beginTransaction();

        kafkaProducer.commitTransaction();
        kafkaProducer.abortTransaction();


        KafkaConsumer<String,String> kafkaConsumer=null;
        kafkaConsumer.commitSync();
    }
}
