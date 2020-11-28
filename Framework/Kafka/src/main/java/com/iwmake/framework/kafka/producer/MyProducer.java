package com.iwmake.framework.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author Dylan
 * @since 2020-11-28
 */
public class MyProducer {
    public static void main(String[] args) {
        Properties properties = new Properties();

        // 指定连接集群
        properties.put("bootstrap.servers", "node01:9092");
        // ACK应答级别 key参数都定义在ProducerConfig配置类中
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        // 重试次数
        properties.put("retries", 1);
        // 批次大小 16k
        properties.put("batch.size", 16384);
        // 等待时间
        properties.put("linger.ms", 1);
        // 缓冲区大小
        properties.put("buffer,memory", 33554432);
        // 序列化器
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 发送消息
        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String, String>("test_partition", i+"--test_value"));
        }

        // 关闭资源
        producer.close();
    }
}
