package com.iwmake.framework.spark.rdd.action

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-20
 */
class ActionOp {

  var conf = new SparkConf().setMaster("local[6]").setAppName("transformation_op")
  var sc = new SparkContext(conf)

  /**
   * 计算所有商品总价
   */
  @Test
  def reduce(): Unit = {
    val rdd = sc.parallelize(Seq(("手机", 10.0), ("手机", 15.0), ("电脑", 20.0)))
    val result = rdd.reduce((curr, agg) => ("总价", curr._2 + agg._2))
    println(result)
  }

  @Test
  def foreach(): Unit = {
    var rdd = sc.parallelize(Seq(1, 2, 3))
    rdd.foreach(item => println(item))
  }

  /**
   * count he countByKey的结果相距很远，
   * 每次调用Action都会生成job，
   * job运行获取结果，所以中间有Log输出，其实就是启动job
   * countByKey的运算结果是Map(key,value) -> key的count
   *
   * 数据倾斜，如果要解决数据倾斜问题，是不是要先知道谁倾斜，
   * 通过countByKey可以查看，对应key的数据量
   */
  @Test
  def count(): Unit = {
    var rdd = sc.parallelize(Seq(("a", 1), ("a", 2), ("c", 3), ("c", 4)))
    println(rdd.count())
    println(rdd.countByKey())
  }

  /**
   * take和takeSample都是获取数据，一个是直接获取，一个是采样获取
   * first：一般情况下，Action会从所有分区获取数据，相对来说速度比较慢，但是first直接获取第一个分区第一个元素
   */
  @Test
  def take(): Unit = {
    var rdd = sc.parallelize(Seq(1, 2, 3, 4, 5, 6))
    rdd.take(3).foreach(item => println(item))
    println(rdd.first())
    rdd.takeSample(withReplacement = false, 3).foreach(item => println(item))
  }

  @Test
  def numeric():Unit = {
    val rdd = sc.parallelize(Seq(1,2,3,4,10,20,30,50,100))
    println(rdd.max())
    println(rdd.min())
    println(rdd.mean())
    println(rdd.sum())
    println(rdd.variance())
    println(rdd.stdev())
  }

}
