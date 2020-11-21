package com.iwmake.framework.spark.theory

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-21
 */
class SourceAnalysis {

  @Test
  def wordCount(): Unit = {
    // 创建sc
    var conf = new SparkConf().setAppName("wordcount_source").setMaster("local[6]")
    var sc = new SparkContext(conf)
    // 创建数据集
    var textRDD = sc.parallelize(Seq("hadoop spark", "hadoop flume", "spark sqoop"))

    // 数据处理
    // 1.拆词
    var splitRDD = textRDD.flatMap(_.split(" "))
    // 2.赋予初始词频
    val tupleRDD = splitRDD.map((_, 1))
    // 3.聚合统计词频
    val reduceRDD = tupleRDD.reduceByKey(_ + _)
    // 4.结果转换为字符串
    val strRDD = reduceRDD.map(item => s"${item._1},${item._2}")

    // 结果获取
    //strRDD.collect().foreach(println(_))
    println(strRDD.toDebugString)

    // 关闭sc，执行
    sc.stop()
  }

}
