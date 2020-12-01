package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * 演示flink Dataset的分区策略
 * @author Dylan
 * @since 2020-11-30
 */
object PartitionDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._


    // 3 转换操作


    // 4 sink 输出


    // 5 执行



  }

}
