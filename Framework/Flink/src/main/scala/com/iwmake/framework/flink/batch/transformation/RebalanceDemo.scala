package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * Rebalance 演示
 * @author Dylan
 * @since 2020-11-30
 */
object RebalanceDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val ds: DataSet[Long] = env.generateSequence(0,100)
    // 过滤掉部分数据保证倾斜现象明显
    val filterDS: DataSet[Long] = ds.filter(_>18)

    // 3 转换操作
    // 如何观测到每个分区的数据量？
    val subTaskDS = filterDS.map(new RichMapFunction[Long, (Long,Long)] { // 以rich为代表的父类可获取到运行时的上下文对象
      // 自定义map逻辑
      override def map(value: Long): (Long, Long) = {
        // 获取当前任务的编号信息
        val subtask = getRuntimeContext.getIndexOfThisSubtask
        (subtask, value)
      }
    })
    // 使用Rebalance解决数据倾斜问题
    val rebalanceDS = filterDS.rebalance()
    val rebDS = rebalanceDS.map(new RichMapFunction[Long, (Long, Long)] { // 以rich为代表的父类可获取到运行时的上下文对象
      // 自定义map逻辑
      override def map(value: Long): (Long, Long) = {
        // 获取当前任务的编号信息
        val subtask = getRuntimeContext.getIndexOfThisSubtask
        (subtask, value)
      }
    })


    // 4 sink 输出
    subTaskDS.print()
    println("=============")
    rebDS.print()

    // 5 执行



  }

}
