package com.iwmake.framework.flink.stream.source

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * @author Dylan
 * @since 2020-12-01
 */
object CollectionSourceDemo {
  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    env.fromElements("")
    env.fromCollection(List())
  }
}
