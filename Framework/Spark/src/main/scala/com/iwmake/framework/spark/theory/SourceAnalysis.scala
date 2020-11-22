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

  @Test
  def narrowDependency(): Unit = {
    // 需求：求得两个RDD之间的笛卡尔积
    // 生成RDD
    var conf = new SparkConf().setMaster("local[6]").setAppName("cartesian")
    var sc = new SparkContext(conf)
    var rdd1 = sc.parallelize(Seq(1,2,3,4,5,6))
    var rdd2 = sc.parallelize(Seq("a","b","c"))

    val resRDD = rdd1.cartesian(rdd2)

    resRDD.collect().foreach(println(_))

    sc.stop()
  }

}
