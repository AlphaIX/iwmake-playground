package com.iwmake.framework.flink.batch.broadcast

import java.util

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

/**
 * @author Dylan
 * @since 2020-12-01
 */
object BroadcastDemo {

  def main(args: Array[String]): Unit = {
    // 1 env运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source 分别创建两个数据集
    import org.apache.flink.api.scala._
    // 学生数据集 广播该数据集
    val stuDS: DataSet[(Int, String)] = env.fromCollection(List((1, "张三"), (2, "李四"), (3, "王五")))
    // 成绩数据集
    val scoreDS: DataSet[(Int, String, Int)] = env.fromCollection(List((1, "语文", 50), (2, "数学", 70), (3, "英语", 86)))

    // 3 转换
    // 3.1 使用RichMapFunction对学生成绩进行map转换
    // 泛型in,out in:map处理的数据类型(Int, String, Int)，out:map输出的数据类型
    val resDS = scoreDS.map(new RichMapFunction[(Int, String, Int), (String, String, Int)] {
      // 3.3.4 定义成员变量接收广播数据
      var stuMap: Map[Int, String] = null

      // 3.3 获取广播变量
      // open方法是Rich函数中比较重要和常用的一个方法，初始化方法，执行一次，此处在open方法中获取广播变量
      override def open(parameters: Configuration): Unit = {
        // 3.3.1 根据名称获取广播变量，需要限定广播变量的数据类型
        val stuList: util.List[(Int, String)] = getRuntimeContext.getBroadcastVariable[(Int, String)]("student")
        // 3.3.2 list转为map数据
        import scala.collection.JavaConverters._
        // 3.3.3 把获取到的广播数据 ，转为map key为学生id，value学生姓名
        stuMap = stuList.asScala.toMap
      }

      // map每一条数据都会进入，不适合直接在map中获取广播变量，应该重写open方法，在open中获取广播变量
      // 3.4 根据stuMap查询学生姓名，返回数据
      override def map(score: (Int, String, Int)): (String, String, Int) = {
        // 3.4.1 获取成绩中的学生id
        val stuId: Int = score._1
        // 3.4.2 获取学生姓名
        val stuName = stuMap.getOrElse(stuId, "null")
        // 3.4.3 组装数据并返回
        (stuName, score._2, score._3)
        //getRuntimeContext.getBroadcastVariable("student")
      }
      // 3.2 广播学生数据
    }).withBroadcastSet(stuDS, "student")

    // 4 sink
    resDS.print()

    // 5 执行
  }

}
