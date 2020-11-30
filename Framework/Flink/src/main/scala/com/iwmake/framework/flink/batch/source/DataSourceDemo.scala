package com.iwmake.framework.flink.batch.source

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * 演示flink中DataSet常见datasource
 *
 * @author Dylan
 * @since 2020-11-30
 */
object DataSourceDemo {
  def main(args: Array[String]): Unit = {
    /**
     * 主要有2类
     * 一 基于集合
     * 二 基于文件
     */
    // 1 获取executionEnvironment
    val env = ExecutionEnvironment.getExecutionEnvironment
    // 2 source操作
    // 2.1 基于集合
    /**
     * env.fromElements() 支持tuple,自定义对象等复合形式
     * env.fromCollection() 支持Collection的具体类型
     * env.generateSequence() 支持创建基于Sequence的DataSet
     */
    import org.apache.flink.api.scala._
    val elsDS: DataSet[String] = env.fromElements("spark", "hadoop","flink")

    val collDS: DataSet[String] = env.fromCollection(Array("spark","hadoop","flink"))

    val seqDS: DataSet[Long] = env.generateSequence(1,9)

    // 3 转换 | 可以没有转换

    // 4 sink输出
    elsDS.print()
    collDS.print()
    seqDS.print()

    // 5 启动 在批处理中：如果sink操作是 count(), collect(), print()，最后不需要执行execute操作，否则报错
    //env.execute()

  }

}
