package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.java.aggregation.Aggregations
import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * Distinct演示  去重
 *
 * @author Dylan
 * @since 2020-11-30
 */
object DistinctDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val sourceDS = env.fromCollection(List(
      ("java",1),("java",2),("scala",1)
    ))

    // 3 转换操作 使用distinct去重
    sourceDS.distinct().print() //对整个元组去重
    sourceDS.distinct(0).print() //指定字段去重

    // 4 sink 输出
    //resDS.print()


    // 5 执行



  }

}
