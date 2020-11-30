package com.iwmake.framework.flink.batch.source

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}

/**
 * 演示基于文件创建DataSet
 *
 * @author Dylan
 * @since 2020-11-30
 */
object DataSourceDemo2 {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment
    // 2基于文件创建DataSet
    // 2.1读取本地文件
    val wordsDS: DataSet[String] = env.readTextFile("0_dataset/input/flink/words.txt")
    // 2.2读取hdfs文件
    val dfsWordsDS: DataSet[String] = env.readTextFile("hdfs://node01:8020/input/words.txt")
    // 2.3读取CSV文件
    case class Subject(id:Int,name:String)
    import org.apache.flink.api.scala._
    val subjectDS: DataSet[Subject] = env.readCsvFile[Subject]("0_dataset/input/flink/subject.csv")
    // 2.4读取压缩文件，压缩文件不能并行读取
    //val compressDS = env.readTextFile("xxxx.gz")
    // 2.5遍历读取文件夹数据
    //val conf = new Configuration()
    //conf.setBoolean("recursive.file.enumeration", true)// 递归遍历读取需要设置此属性
    //env.readTextFile("xx/input").withParameters(conf)


    // 打印输出结果
    wordsDS.print()
    println("-------------------------------")
    dfsWordsDS.print()
    println("-------------------------------")
    subjectDS.print()

  }

}
