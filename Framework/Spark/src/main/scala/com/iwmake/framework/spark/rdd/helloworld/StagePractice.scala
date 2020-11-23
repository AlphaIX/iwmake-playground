package com.iwmake.framework.spark.rdd.helloworld

import org.apache.commons.lang3.StringUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-20
 */
class StagePractice {

  @Test
  def pmProcess(): Unit = {
    // 1.创建sc对象
    var conf = new SparkConf().setMaster("local[6]").setAppName("pm_process")
    var sc = new SparkContext(conf)

    // 2.读取文件
    val source = sc.textFile("dataset/input/beijing_pm_data.csv")
    //    val source = sc.textFile("dataset/input/test.csv")

    // 3.通过算子处理数据
    // 3.1抽取数据 日期[年月日]  PM类型  东四pm值
    // 3.2清洗
    // 3.3聚合
    // 3.4排序
    val rdd = source.map(item => ((item.split(",")(0), item.split(",")(2)), item.split(",", -1)(3)))
      .filter(item => item._1._2.equals("PM2.5"))
      .map(item => (item._1._1, item._2))
      .filter(item => StringUtils.isNotEmpty(item._2))
      .map(item => (item._1.substring(0, 6), item._2.toInt))
      .reduceByKey((curr, agg) => curr + agg)
      .sortBy(item => item._2, ascending = false)

    //println(rdd.count())
    //println(rdd.first())

    // 4.获取结果
    rdd.take(10).foreach(item => println(item))

    // 5.关闭sc
    sc.stop()
  }

}
