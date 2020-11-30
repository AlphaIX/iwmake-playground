package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * map 和mapPartition演示
 * @author Dylan
 * @since 2020-11-30
 */
object MapAndMapPartitionDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val sourceDS = env.fromCollection(List("1,张三","2,李四", "3,王五","4,赵六"))

    // 3 转换操作
    // 3.1 定义case class
    case class Student(id:Int, name:String)
    // 3.2 map操作
    val stuDS = sourceDS.map(item => {
      val arr = item.split(",")
      Student(arr(0).toInt, arr(1))
    })
    // 3.3 mappartition操作
    val stuDS2 = sourceDS.mapPartition( iter => {
      // 遍历迭代器数据 转为case class类型
      iter.map(item => {
        val arr = item.split(",")
        Student(arr(0).toInt, arr(1))
      })
    })

    // 4 sink 输出
    stuDS.print()
    println("================")
    stuDS2.print()
    // 5 执行



  }

}
