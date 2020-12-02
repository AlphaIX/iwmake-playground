package com.iwmake.framework.flink.stream.sink

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011
import org.apache.flink.streaming.util.serialization.KeyedSerializationSchemaWrapper
import org.apache.kafka.clients.producer.ProducerConfig

/**
 * flink程序 计算结果保存到Kafka中
 *
 * @author Dylan
 * @since 2020-12-02
 */
// 定义student样例类
case class Student(id: Int, name: String, age: Int)

object SinkToKafkaDemo {
  def main(args: Array[String]): Unit = {
    // 1. 创建流处理运行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 2. 加载source
    import org.apache.flink.api.scala._
    val stuDS: DataStream[Student] = env.fromElements(Student(1, "tony", 18))

    // 3. 直接使用kafka Producer
    // 3.1 准备一个Kafka Producer对象
    val topic = "test"
    val keyedSerializationWrapper: KeyedSerializationSchemaWrapper[String] = new KeyedSerializationSchemaWrapper(new SimpleStringSchema)
    val props = new Properties()
    props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node01:9092,node02:9092")
    val flinkKafkaProducer: FlinkKafkaProducer011[String] = new FlinkKafkaProducer011[String](topic, keyedSerializationWrapper, props)

    // 4. sink操作
    stuDS.map(_.toString).addSink(flinkKafkaProducer)

    // 5. 执行
    env.execute()

  }
}

