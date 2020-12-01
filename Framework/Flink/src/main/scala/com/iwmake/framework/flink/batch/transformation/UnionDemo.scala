package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * Union演示  取并集，不去重，DataSet中数据类型必须保持一致
 *
 * @author Dylan
 * @since 2020-11-30
 */

object UnionDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val ds1: DataSet[String] = env.fromCollection(List("hadoop","hive","flume"))
    val ds2: DataSet[String] = env.fromCollection(List("hadoop","hive","azkaban"))

    // 3 转换操作 union 要求两个DataSet中的数据类型必须一致
    val unionDS: DataSet[String] = ds1.union(ds2)

    // 4 sink 输出
    unionDS.print()

    // 5 执行



  }

}
