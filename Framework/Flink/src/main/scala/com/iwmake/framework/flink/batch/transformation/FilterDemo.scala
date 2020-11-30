package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * filter演示
 * 过滤长度大于4的单词
 * @author Dylan
 * @since 2020-11-30
 */
object FilterDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val sourceDS = env.fromCollection(List(
      "hadoop","hive","spark","flink"
    ))

    // 3 转换操作
    val filterDS = sourceDS.filter(_.length > 4)

    // 4 sink 输出
    filterDS.print()


    // 5 执行



  }

}
