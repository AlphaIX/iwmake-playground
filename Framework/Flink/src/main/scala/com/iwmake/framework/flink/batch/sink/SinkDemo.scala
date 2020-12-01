package com.iwmake.framework.flink.batch.sink

import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.core.fs.FileSystem

/**
 * 演示flink Dataset 中 sink 操作
 *
 * @author Dylan
 * @since 2020-12-01
 */
object SinkDemo {
  def main(args: Array[String]): Unit = {
    // 1.env
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    // 2.source
    import org.apache.flink.api.scala._
    val ds: DataSet[String] = env.fromElements("spark", "hadoop", "flink")

    // 3.转换

    // 4.sink
    // 4.1基于集合
    //    // 标准输出
    //    ds.print()
    //    // 错误输出
    //    ds.printToErr()
    //    // collect到本地
    //    print(ds.collect())

    // 4.2 基于文件
    // 可以直接调整并行度
    ds.setParallelism(1).writeAsText("0_dataset/output/flink/sinkwords", FileSystem.WriteMode.OVERWRITE)
    // 如果只有一个并行度最后会保存为一个文件，如果多个并行度会在文件夹中保存多个文件

    // 5.执行
    env.execute()

  }
}
