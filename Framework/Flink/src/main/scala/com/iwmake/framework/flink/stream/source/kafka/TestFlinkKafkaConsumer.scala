package com.iwmake.framework.flink.stream.source.kafka

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011
import org.apache.kafka.clients.consumer.ConsumerConfig

/**
 * @author Dylan
 * @since 2020-12-02
 */
object TestFlinkKafkaConsumer {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // kafka配置,最基本配置，更多配置参考官网
    val props: Properties = new Properties()
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "node01:9092")
    props.put(ConsumerConfig.GROUP_ID_CONFIG, "flink")
    props.put("flink.partition-discovery.interval-millis", "5000")
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")
    // topic
    val topic = "test"
    val flinkKafkaConsumer: FlinkKafkaConsumer011[String] = new FlinkKafkaConsumer011[String](topic, new SimpleStringSchema(), props)

    import org.apache.flink.api.scala._
    // Kafka Source
    val kafkaDS: DataStream[String] = env.addSource(flinkKafkaConsumer)

    // 打印数据
    kafkaDS.print()

    // 启动执行
    env.execute()

  }
}
