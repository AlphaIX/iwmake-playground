package com.iwmake.framework.spark.rdd.helloworld

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

class WordCount {
  def main(args: Array[String]): Unit = {
    // 1.创建SparkContext
    var conf = new SparkConf().setAppName("word_count").setMaster("local[6]")
    var sc = new SparkContext(conf)

    // 2.加载数据文件
    // 2.1准备文件 工程根目录下dataset
    // 2.2读取文件
    var rdd1 = sc.textFile("dataset/input/wordcount.txt") // 相对路径

    // 3.处理
    // 3.1拆分为多个单词
    val rdd2 = rdd1.flatMap(item => item.split(" "))
    // 3.2把每个单词指定一个词频1
    var rdd3 = rdd2.map(item => (item, 1))
    // 3.3聚合
    var rdd4 = rdd3.reduceByKey((curr, agg) => curr + agg)

    // 4.得到结果
    var result = rdd4.collect()
    result.foreach(item => println(item))
  }

  /** 创建SparkContext */
  @Test
  def sparkContext(): Unit = {
    // 1.Spark Context如何编写
    // 1.1 创建SparkConf
    var conf = new SparkConf().setMaster("local[6]").setAppName("spark_context")
    // 1.2 创建SparkContext
    var sc = new SparkContext(conf)
    // SparkContext身为Context大入口API，具有创建RDD，设置参数，jars...

    // 关闭SparkContext 释放集群资源
  }


  var conf = new SparkConf().setMaster("local[6]").setAppName("spark_context")
  // 1.2 创建SparkContext
  var sc = new SparkContext(conf)


  /** 创建RDD第一种方式：从本地集合创建 */
  @Test
  def rddCreationLocal(): Unit = {
    var seq = Seq(1, 2, 3)
    val rdd1: RDD[Int] = sc.parallelize(seq, 2)
    val rdd2: RDD[Int] = sc.makeRDD(seq, 2)
  }

  /** 创建RDD第二种方式：从文件创建 */
  @Test
  def rddCreationFiles(): Unit = {
    sc.textFile("file:///....")
    // path传入什么？
    // 传入支持的文件系统URI，可以是HDFS，本地文件系统，aws，阿里云等
    // 如果是hdfs，第二个参数分区是由hdfs文件的block数决定的
  }

  /** 创建RDD第三种方式：从RDD衍生 */
  @Test
  def rddCreationFromRDD(): Unit = {
    var rdd1 = sc.parallelize(Seq(1, 2, 3))
    // 通过在RDD上执行算子操作，生成一个新的RDD
    var rdd2: RDD[Int] = rdd1.map(item => item)
  }

  /**
   * 测试map算子
   */
  @Test
  def mapTest(): Unit = {
    // 1.创建RDD
    var rdd1 = sc.parallelize(Seq(1, 2, 3))
    // 2.执行map操作
    val rdd2 = rdd1.map(item => item * 10)
    // 3.得到结果
    var result = rdd2.collect()
    result.foreach(item => println(item))
  }

  /**
   * 测试flatMap算子
   */
  @Test
  def flatMapTest(): Unit = {
    // 1.创建RDD
    var rdd1 = sc.parallelize(Seq("hello lily", "hello lucy", "hello tim"))
    // 2.处理数据
    var rdd2 = rdd1.flatMap(item => item.split(" "))
    // 3.得到结果
    var result = rdd2.collect()
    result.foreach(item => println(item))
    // 4.关闭sc
    sc.stop()
  }

  /**
   * 测试reduceByKey算子
   */
  @Test
  def reduceByKeyTest(): Unit = {
    // 创建RDD
    var rdd1 = sc.parallelize(Seq("hello lily", "hello lucy", "hello tim"))
    // 处理数据
    var rdd2 = rdd1
      .flatMap(item => item.split(" "))
      .map(item => (item, 1))
      .reduceByKey((curr, agg) => curr + agg)
    // 得到结果
    var result = rdd2.collect()
    result.foreach(item => println(item))
    // 关闭sc
    sc.stop()
  }
}