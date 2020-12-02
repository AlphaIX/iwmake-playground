package com.iwmake.framework.flink.stream.transformation

import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * 演示keyBy使用
 *
 * @author Dylan
 * @since 2020-12-02
 */
object KeyByDemo {

  def main(args: Array[String]): Unit = {
    // 1 创建StreamEnv
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // 2 加载SocketStream
    val socketDS = env.socketTextStream("node01", 9999)

    // 3 转换
    import org.apache.flink.api.scala._
    val wordDS = socketDS.flatMap(_.split(" ")).map((_,1))
    // keyBy
    wordDS.keyBy(0).sum(1).print()

    // 启动
    env.execute()
  }

}
