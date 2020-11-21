package com.iwmake.framework.spark.cache

import org.apache.commons.lang3.StringUtils
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-21
 */
class CacheOp {

  /**
   * 1.创sc
   * 2.读取文件
   * 3.取出IP，赋予初始频率
   * 4.清洗
   * 5.统计IP出现的次数
   * 6.统计出现次数最少的IP
   * 7.统计出现次数最多的IP
   */
  @Test
  def prepare(): Unit = {
    var conf = new SparkConf().setAppName("cache_prepare").setMaster("local[6]")
    var sc = new SparkContext(conf)

    val source = sc.textFile("dataset/input/access_log_sample.txt")

    var countRDD = source.map(item => (item.split(",")(0), 1))

    var cleanRDD = countRDD.filter(item => StringUtils.isNoneEmpty(item._1))

    var aggRDD = cleanRDD.reduceByKey((curr, agg) => curr + agg)

    var lessIp = aggRDD.sortBy(item => item._2, ascending = true).first()
    var moreIp = aggRDD.sortBy(item => item._2, ascending = false).first()

    println(lessIp, moreIp)

  }

  @Test
  def cache(): Unit = {
    var conf = new SparkConf().setAppName("cache_prepare").setMaster("local[6]")
    var sc = new SparkContext(conf)

    // RDD处理部分
    val source = sc.textFile("dataset/input/access_log_sample.txt")
    var countRDD = source.map(item => (item.split(",")(0), 1))
    var cleanRDD = countRDD.filter(item => StringUtils.isNoneEmpty(item._1))
    var aggRDD = cleanRDD.reduceByKey((curr, agg) => curr + agg)

    aggRDD = aggRDD.cache()

    // 两个RDD Action操作，每一个Action都会运行一下RDD整个血统
    var lessIp = aggRDD.sortBy(item => item._2, ascending = true).first()
    var moreIp = aggRDD.sortBy(item => item._2, ascending = false).first()

    println(lessIp, moreIp)

  }

  @Test
  def persist(): Unit = {
    var conf = new SparkConf().setAppName("cache_prepare").setMaster("local[6]")
    var sc = new SparkContext(conf)

    // RDD处理部分
    val source = sc.textFile("dataset/input/access_log_sample.txt")
    var countRDD = source.map(item => (item.split(",")(0), 1))
    var cleanRDD = countRDD.filter(item => StringUtils.isNoneEmpty(item._1))
    var aggRDD = cleanRDD.reduceByKey((curr, agg) => curr + agg)

    aggRDD = aggRDD.persist(StorageLevel.MEMORY_ONLY)
    println(aggRDD.getStorageLevel)

    //    // 两个RDD Action操作，每一个Action都会运行一下RDD整个血统
    //    var lessIp = aggRDD.sortBy(item => item._2, ascending = true).first()
    //    var moreIp = aggRDD.sortBy(item => item._2, ascending = false).first()
    //
    //    println(lessIp, moreIp)

  }

  @Test
  def checkpoint(): Unit = {
    var conf = new SparkConf().setAppName("cache_prepare").setMaster("local[6]")
    var sc = new SparkContext(conf)
    // 设置保存checkpoint的目录，也可以设置为hdfs上的目录
    sc.setCheckpointDir("dataset/checkpoint")

    // RDD处理部分
    val source = sc.textFile("dataset/input/access_log_sample.txt")
    var countRDD = source.map(item => (item.split(",")(0), 1))
    var cleanRDD = countRDD.filter(item => StringUtils.isNoneEmpty(item._1))
    var aggRDD = cleanRDD.reduceByKey((curr, agg) => curr + agg)

    // checkpoint, 类似一个Action操作
    // 如果调用checkpoint，则会重新计算一下RDD，然后把结果存在HDFS，或者本地目录中
    // 所以应该在checkpoint之前，进行一次cache
    aggRDD = aggRDD.cache()
    aggRDD.checkpoint()

    // 两个RDD Action操作，每一个Action都会运行一下RDD整个血统
    var lessIp = aggRDD.sortBy(item => item._2, ascending = true).first()
    var moreIp = aggRDD.sortBy(item => item._2, ascending = false).first()

    println(lessIp, moreIp)

  }

}
