package com.iwmake.framework.flink.stream.transformation

import org.apache.flink.streaming.api.scala.{DataStream, SplitStream, StreamExecutionEnvironment}

/**
 * @author Dylan
 * @since 2020-12-02
 */
object SplitSelectDemo {
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    import org.apache.flink.api.scala._
    val numDS: DataStream[Int] = env.fromCollection(List(1, 2, 3, 4, 5, 6, 7, 8))
    // split把数据流分为奇数和偶数
    val splitStream: SplitStream[Int] = numDS.split(item => {
      var res = item % 2
      // 模式匹配方式
      res match {
        case 0 => List("even") // 偶数 oven和odd只是名称，代表数据流的名称，但是必须放在可迭代List集合中
        case 1 => List("odd") // 奇数
      }
    })
    // 从splitStream中获取奇数流 偶数流
    val evenDS = splitStream.select("even")
    val oddDS = splitStream.select("odd")
    val allDS = splitStream.select("even", "odd")

    evenDS.print()
    //oddDS.print()
    //allDS.print()

    env.execute()
  }
}
