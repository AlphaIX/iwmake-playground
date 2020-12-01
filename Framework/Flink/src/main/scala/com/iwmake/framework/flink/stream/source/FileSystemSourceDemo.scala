package com.iwmake.framework.flink.stream.source

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * @author Dylan
 * @since 2020-12-01
 */
object FileSystemSourceDemo {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.readTextFile("xxx.csv")
    env.readTextFile("hdfs://node01/xxx")
  }
}
