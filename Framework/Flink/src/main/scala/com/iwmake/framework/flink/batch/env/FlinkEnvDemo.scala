package com.iwmake.framework.flink.batch.env

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * Evn演示
 *
 * @author Dylan
 * @since 2020-12-01
 */
object FlinkEnvDemo {
  def main(args: Array[String]): Unit = {
    val localEnv = ExecutionEnvironment.createLocalEnvironment()
    val collectionEnv = ExecutionEnvironment.createCollectionsEnvironment
    // 推荐使用 根据不同的环境获取env对象
    val env = ExecutionEnvironment.getExecutionEnvironment
  }
}
