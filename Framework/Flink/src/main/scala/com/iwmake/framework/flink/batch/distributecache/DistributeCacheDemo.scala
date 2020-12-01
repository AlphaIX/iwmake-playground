package com.iwmake.framework.flink.batch.distributecache

import java.io.File
import java.util

import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.ExecutionEnvironment
import org.apache.flink.configuration.Configuration

import scala.io.Source

/**
 * @author Dylan
 * @since 2020-12-01
 */
object DistributeCacheDemo {

  def main(args: Array[String]): Unit = {
    // 1 env运行环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source 分别创建成绩数据集
    import org.apache.flink.api.scala._

    // 成绩数据集
    val scoreDS: DataSet[(Int, String, Int)] = env.fromCollection(List((1, "语文", 50), (2, "数学", 70), (3, "英语", 86)))
    // 将学生数据注册为分布式缓存文件
    env.registerCachedFile("hdfs://node01:8020/xxx", "cache_student")

    // 3 转换
    // 3.1 使用RichMapFunction对学生成绩进行map转换
    val resDS = scoreDS.map(new RichMapFunction[(Int, String, Int), (String, String, Int)] {
      // 3.3 定义一个map成员变量
      var stuMap: Map[Int, String] = null

      // 3.2 获取上下文缓存文件
      override def open(parameters: Configuration): Unit = {
        val file: File = getRuntimeContext.getDistributedCache.getFile("cache_student")
        //对文件进行读取和解析
        val tuples: Iterator[(Int, String)] = Source.fromFile(file).getLines().map(
          line => {
            val arr = line.split(",")
            (arr(0).toInt, arr(1))
          }
        )
        // 得到一个ID作为key 姓名是value的map
        stuMap = tuples.toMap
      }

      // 3.4 根据stuMap解析学生姓名返回map
      override def map(score: (Int, String, Int)): (String, String, Int) = {
        // 3.4.1 获取成绩中的学生id
        val stuId: Int = score._1
        // 3.4.2 获取学生姓名
        val stuName = stuMap.getOrElse(stuId, "null")
        // 3.4.3 组装数据并返回
        (stuName, score._2, score._3)
      }
    })

    // 4 sink
    resDS.print()

    // 5 执行
  }

}
