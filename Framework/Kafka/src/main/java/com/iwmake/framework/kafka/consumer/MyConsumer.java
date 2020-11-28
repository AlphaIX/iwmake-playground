package com.iwmake.framework.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

/**
 * @author Dylan
 * @since 2020-11-28
 */
public class MyConsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();

        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"node01:9092");

        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");

        // 重置消费者offset
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        // 消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG,"data1");

        // 创建消费者对象
        KafkaConsumer consumer = new KafkaConsumer<String, String>(properties);
        // 订阅主题
        consumer.subscribe(Arrays.asList("aaa","bbb"));

        // 获取数据
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String,String> record: records){
                System.out.println(record.key() +"----"+record.value());
            }
        }
    }
}
