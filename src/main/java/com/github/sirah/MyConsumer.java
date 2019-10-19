package com.github.sirah;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
public class MyConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-consumer-1");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        System.out.println("Subscribing to `sample` topic");
        consumer.subscribe(Arrays.asList("sample"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(400));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Received offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
            }
        }
    }
}