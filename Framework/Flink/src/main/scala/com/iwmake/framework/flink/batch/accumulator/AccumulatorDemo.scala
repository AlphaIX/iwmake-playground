package com.iwmake.framework.flink.batch.accumulator

import org.apache.flink.api.common.JobExecutionResult
import org.apache.flink.api.common.accumulators.IntCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration
import org.apache.flink.core.fs.FileSystem

/**
 * flink中累加器使用
 *
 * @author Dylan
 * @since 2020-12-01
 */
object AccumulatorDemo {
  def main(args: Array[String]): Unit = {
    // 1. 获取批处理环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2. source加载数据
    import org.apache.flink.api.scala._
    val wordDS: DataSet[String] = env.fromCollection(List("aaaa", "bbbb", "cccc", "dddd"))

    // 3 转换，不能使用普通方法，需要使用Rich方法，因为需要注册累加器
    //
    val accDS = wordDS.map(
      // 3.1 泛型 in:String，out:String,不处理数据，只是统计数量
      new RichMapFunction[String, String] {
        // 3.2 创建累加器
        private val numLines = new IntCounter()
        // 对比定义一个 Int类型变量
        var num = 0

        override def open(parameters: Configuration): Unit = {
          // 3.3 注册累加器
          getRuntimeContext.addAccumulator("numLines", numLines)
        }

        override def map(value: String): String = {
          // 3.4 使用累加器 统计数据条数
          numLines.add(1) // 向累加器中增加1
          num += 1
          println(num)
          value
        }
      }).setParallelism(2)

    // 4 sink 保存到文件
    accDS.writeAsText("0_dataset/output/flink/accumulator", FileSystem.WriteMode.OVERWRITE)

    // 5. 启动执行
    val res: JobExecutionResult = env.execute()

    // 6. 获取累加器的值
    val acc = res.getAccumulatorResult[Int]("numLines")
    println("acc结果值：" + acc)
  }
}
