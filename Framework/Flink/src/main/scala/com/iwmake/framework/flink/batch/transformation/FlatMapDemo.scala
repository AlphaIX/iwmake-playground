package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * flatMap演示
 * @author Dylan
 * @since 2020-11-30
 */
object FlatMapDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val sourceDS = env.fromCollection(List(
      "张三,中国,江西省,南昌市",
      "李四,中国,河北省,石家庄市"
    ))

    // 3 转换操作
    // 数据条数变多可以考虑flatMap
    val flatMapDS = sourceDS.flatMap(item => {
      val arr = item.split(",")
      List(
        (arr(0),arr(1)),
        (arr(0),arr(1),arr(2)),
        (arr(0),arr(1),arr(2),arr(3))
      )
    })


    // 4 sink 输出
    flatMapDS.print()

    // 5 执行



  }

}
