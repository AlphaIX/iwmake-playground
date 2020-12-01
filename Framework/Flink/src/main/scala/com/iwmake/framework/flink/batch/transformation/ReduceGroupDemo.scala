package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * ReduceGroup演示
 *
 * @author Dylan
 * @since 2020-11-30
 */
object ReduceGroupDemo {

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

    // 3 转换操作 使用reduceGroup实现单词计算
    // reduceGroup预先聚合减少网络传输，可提供性能
    val resDS2 = sourceDS2.groupBy(_._1).reduceGroup(iter => {
      iter.reduce((w1,w2) => (w1._1,w1._2+w2._2))
    })

    // 4 sink 输出
    resDS2.print()

    // 5 执行



  }

}
