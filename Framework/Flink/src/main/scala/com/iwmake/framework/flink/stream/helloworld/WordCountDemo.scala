package com.iwmake.framework.flink.stream.helloworld

import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.scala.{DataStream, KeyedStream, StreamExecutionEnvironment, WindowedStream}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow

/**
 * 流式计算的wordCount
 *
 * @author Dylan
 * @since 2020-12-01
 */
object WordCountDemo {
  def main(args: Array[String]): Unit = {
    // 1 创建一个流处理运行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 2 构建Socket source数据源 , 返回类型是DataStream
    val socketDS: DataStream[String] = env.socketTextStream("node01", 9999)

    // 3 接收到的数据转为(单词,1)
    import org.apache.flink.api.scala._
    val tupleDS: DataStream[(String, Int)] = socketDS.flatMap(_.split(" ")).map((_,1))

    // 4 对元组使用keyBy分组,类似批处理中的groupBy
    val keyedStream: KeyedStream[(String, Int), Tuple] = tupleDS.keyBy(0)

    // 5 使用窗口进行5s的计算，每5s计算一次
    val windowStream: WindowedStream[(String, Int), Tuple, TimeWindow] = keyedStream.timeWindow(Time.seconds(5))

    // 6 sum出单词数量
    val resDS: DataStream[(String, Int)] = windowStream.sum(1)

    // 7 打印输出
    resDS.print()

    // 8 执行
    env.execute()

  }
}
