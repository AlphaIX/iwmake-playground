package com.iwmake.framework.flink.helloworld

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.core.fs.FileSystem

/**
 * 使用flink批处理进行单词计数
 *
 * @author Dylan
 * @since 2020-11-30
 */
object WordCountDemo {
  def main(args: Array[String]): Unit = {
    /**
     * 1 获得一个execution environment
     * 2 加载/创建初始数据
     * 3 指定这些数据的转换
     * 4 指定计算结果放在哪里
     * 5 触发程序执行
     */
    // 1
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    // 设置全局并行度
    env.setParallelism(1)
    // 2
    import org.apache.flink.api.scala._
    val sourceDS: DataSet[String] = env.fromElements("Apache Flink is a framework and distributed processing engine for stateful computations over unbounded and bounded data streams " +
      "Flink has been designed to run in all common cluster environments " +
      "perform computations at in-memory speed and at any scale")
    // 3 大致思路，对每行数据按照空格切分，(单词,1)tuple ,然后聚合
    val wordsDS = sourceDS.flatMap(_.split(" "))
    val wordAndOneDS = wordsDS.map((_, 1))
    val groupDS = wordAndOneDS.groupBy(0)
    val aggDS = groupDS.sum(1)
    // 4
    aggDS.writeAsText("hdfs://node01:8020/wc/out1", FileSystem.WriteMode.OVERWRITE)
    // 5
    env.execute()
  }

}
