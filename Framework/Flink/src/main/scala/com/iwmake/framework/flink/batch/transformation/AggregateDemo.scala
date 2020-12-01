package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.java.aggregation.Aggregations
import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * Aggregate演示
 *
 * @author Dylan
 * @since 2020-11-30
 */
object AggregateDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val sourceDS = env.fromCollection(List(
      ("java",1),("java",1),("scala",1)
    ))

    // 3 转换操作 使用Aggregation
    // aggregate只能作用于元组类型，并且group只能按照索引分组
    //val resDS = sourceDS.groupBy(_._1).aggregate(Aggregations.SUM,1)
    val resDS = sourceDS.groupBy(0).aggregate(Aggregations.SUM,1)
    // 4 sink 输出
    resDS.print()


    // 5 执行



  }

}
