package com.iwmake.framework.flink.batch.env

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * RemoteEvn演示
 * 通过idea提交远程集群
 *
 * @author Dylan
 * @since 2020-12-01
 */
object FlinkRemoteEnvDemo {
  def main(args: Array[String]): Unit = {

    // 创建远程集群环境，连接到jobManager
    val env: ExecutionEnvironment = ExecutionEnvironment.createRemoteEnvironment("node01",8081,"/Users/ydy/Code/basis/progress/iwmake-playground/Framework/Flink/target/demo-flink-1.0-SNAPSHOT.jar")

    val resDS = env.readTextFile("hdfs://node01/input/words.txt")

    resDS.print()
  }
}
