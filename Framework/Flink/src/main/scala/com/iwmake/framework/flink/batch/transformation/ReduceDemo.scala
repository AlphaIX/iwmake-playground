package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * Reduce演示
 *
 * @author Dylan
 * @since 2020-11-30
 */
object ReduceDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val sourceDS = env.fromCollection(List(
      ("java",1),("java",1),("java",1)
    ))
    val sourceDS2 = env.fromCollection(List(
      ("java",1),("java",1),("scala",1)
    ))

    // 3 转换操作 使用reduce累加计算词频
    val resDS = sourceDS.reduce((word,word2) => (word._1,word._2+word2._2))
    // 分组操作后 再累加
    val resDS2 = sourceDS2.groupBy(_._1).reduce((word,word2) => (word._1,word._2+word2._2))
    // 按照索引分组，然后sum
    val resDS3 = sourceDS2.groupBy(0).sum(1)

    // 4 sink 输出
    resDS.print()
    println("===========")
    resDS2.print()
    println("===========")
    resDS3.print()

    // 5 执行



  }

}
