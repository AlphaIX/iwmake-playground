package com.iwmake.framework.spark.rdd.helloworld

import org.apache.commons.lang3.StringUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-20
 */
class AccessLogAgg {

  @Test
  def ipAgg(): Unit = {
    // 1.创建SparkContext
    var conf = new SparkConf().setMaster("local[6]").setAppName("ip_agg")
    var sc = new SparkContext(conf)

    // 2.读取文件，生成数据集
    var sourceRDD = sc.textFile("dataset/input/access_log_sample.txt")

    // 3.取出IP，赋予出现次数为1
    val ipRDD = sourceRDD.map(item => (item.split(",")(0), 1))

    // 4.简单清洗
    // 4.1去掉空数据
    // 4.2去掉非法数据
    // 4.3根据业务，再规整一下数据
    val cleanRDD = ipRDD.filter(item => StringUtils.isNoneEmpty(item._1))

    // 5.根据ip和出现的次数聚合
    val ipAggRDD = cleanRDD.reduceByKey((curr, agg) => curr + agg)

    // 6.排序
    val sortedRDD = ipAggRDD.sortBy(item => item._2, ascending = false)

    // 7.取出结果打印结果
    sortedRDD.take(10).foreach(item => println(item))
  }

}
