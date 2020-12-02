package com.iwmake.framework.flink.stream.sink

import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.connectors.redis.RedisSink
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig
import org.apache.flink.streaming.connectors.redis.common.mapper.{RedisCommand, RedisCommandDescription, RedisMapper}

/**
 * flink程序 计算结果保存到redis中
 * 使用flink 提供的redisSink
 *
 * @author Dylan
 * @since 2020-12-02
 */
// 从Socket接收数据然后计算出单词次数，最终使用RedisSink写数据导redis中
object SinkToRedisDemo {
  def main(args: Array[String]): Unit = {
    // 1. 创建流处理运行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 2. 加载Socket数据
    val socketDS = env.socketTextStream("node01", 9999)
    // 单词计数逻辑
    import org.apache.flink.api.scala._
    // 3. 转换
    val resDS: DataStream[(String, Int)] = socketDS.flatMap(_.split(" ")).map((_, 1)).keyBy(0).sum(1)

    // 4. redis sink
    // 4.1 redis sink构造参数
    val config = new FlinkJedisPoolConfig.Builder()
      .setHost("localhost")
      .setPort(6379)
      .setPassword("redis_2018")
      .build()

    resDS.addSink(new RedisSink[(String, Int)](config, new MyRedisMapper))

    // 5. 执行
    env.execute()

  }
}

// 泛型就是写入redis的数据类型
class MyRedisMapper extends RedisMapper[(String, Int)] {
  // 获取命令描述器，确定数据结构
  override def getCommandDescription: RedisCommandDescription = {
    // 指定使用hset命令，并提供hash结构的第一个key
    new RedisCommandDescription(RedisCommand.HSET, "REDISSINK")
  }

  // 指定key
  override def getKeyFromData(data: (String, Int)): String = {
    data._1
  }

  // 指定value
  override def getValueFromData(data: (String, Int)): String = {
    data._2.toString
  }
}

