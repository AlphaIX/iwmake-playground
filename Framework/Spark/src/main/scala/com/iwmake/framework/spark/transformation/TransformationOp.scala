package com.iwmake.framework.spark.transformation

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-20
 */
class TransformationOp {

  var conf = new SparkConf().setMaster("local[6]").setAppName("transformation_op")
  var sc = new SparkContext(conf)

  /**
   * mapPartitions和map算子是一样的，只不过mapPartitions是针对一个分区的数据转换
   * map针对每一条数据转换
   * mapPartitions返回一个集合
   */
  @Test
  def mapPartitions(): Unit = {
    // 1 数据生成
    // 2 算子使用
    // 3获取结果
    sc.parallelize(Seq(1, 2, 3, 4, 5, 6), 2)
      .mapPartitions(iter => {
        iter.foreach(item => println(item))
        iter
      })
      .collect()
  }

  @Test
  def mapPartitions2(): Unit = {
    // 1 数据生成
    // 2 算子使用
    // 3获取结果
    sc.parallelize(Seq(1, 2, 3, 4, 5, 6), 2)
      .mapPartitions(iter => {
        // 遍历Iter中，对其中每一条数据进行转换，返回整个iter
        iter.map(item => item * 10)
      })
      .collect()
      .foreach(item => println(item))
  }

  /**
   * mapPartitionsWithIndex比mapPartitions多了一个分区号
   */
  @Test
  def mapPartitionsWithIndex(): Unit = {
    sc.parallelize(Seq(1, 2, 3, 4, 5, 6), 2)
      .mapPartitionsWithIndex((index, iter) => {
        println("index: " + index)
        iter.foreach(item => println(item))
        iter
      })
      .collect()
  }

  /**
   * filter可以过滤掉数据集中一部分元素
   */
  @Test
  def filter(): Unit = {
    sc.parallelize(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
      .filter(item => item % 2 == 0)
      .collect()
      .foreach(item => println(item))
  }

  /**
   * 作用：把大数据集变小，尽可能减少数据集规律损失
   */
  @Test
  def sample(): Unit = {
    sc.parallelize(Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
      .sample(withReplacement = true, 0.6)
      .collect()
      .foreach(item => println(item))
  }

  /**
   * mapValues,也是map，不过只针对value值操作
   */
  @Test
  def mapValues(): Unit = {
    sc.parallelize(Seq(("a", 1), ("b", 2), ("c", 3)))
      .mapValues(item => item * 10)
      .collect()
      .foreach(println(_))
  }

  /**
   * 交集
   */
  @Test
  def intersection(): Unit = {
    var rdd1 = sc.parallelize(Seq(1, 2, 3, 4, 5))
    var rdd2 = sc.parallelize(Seq(3, 4, 5, 6, 7))
    rdd1.intersection(rdd2)
      .collect()
      .foreach(println(_))
  }

  /**
   * 并集
   */
  @Test
  def union(): Unit = {
    var rdd1 = sc.parallelize(Seq(1, 2, 3, 4, 5))
    var rdd2 = sc.parallelize(Seq(3, 4, 5, 6, 7))
    rdd1.union(rdd2)
      .collect()
      .foreach(println(_))
  }

  /**
   * 差集
   */
  @Test
  def subtract(): Unit = {
    var rdd1 = sc.parallelize(Seq(1, 2, 3, 4, 5))
    var rdd2 = sc.parallelize(Seq(3, 4, 5, 6, 7))
    rdd1.subtract(rdd2)
      .collect()
      .foreach(println(_))
  }

  /**
   * groupByKey 运算结果的格式(key, (val1,val2...))
   * reduceByKey 能不能在Map端做combiner： 能==>能减少IO
   * groupByKey 在map端做combiner 没有意义
   */
  @Test
  def groupByKey(): Unit = {
    sc.parallelize(Seq(("a", 1), ("a", 1), ("b", 1)))
      .groupByKey()
      .collect()
      .foreach(println(_))
  }

  /**
   * combineByKey 这个算子接收三个参数
   * 转换数据函数，作用第一条数据，用于开启整个计算，
   * 第二个函数在分区上聚合，最后把所有分区的聚合结果聚合为最终结果
   */
  @Test
  def combineByKey(): Unit = {
    // 1.准备集合
    var rdd: RDD[(String, Double)] = sc.parallelize(Seq(
      ("zhangsan", 99.0),
      ("zhangsan", 96.0),
      ("lisi", 97.0),
      ("lisi", 98.0),
      ("zhangsan", 97.0)
    ))
    // 2.算子操作
    // 2.1 createCombiner转换数据
    // 2.2 mergeValue分区上的聚合
    // 2.3 mergeCombiners 把所有分区上的聚合结果，再次聚合，生成最终结果
    val combineResult = rdd.combineByKey(
      createCombiner = (curr: Double) => (curr, 1),
      mergeValue = (curr: (Double, Int), nextValue: Double) => (curr._1 + nextValue, curr._2 + 1),
      mergeCombiners = (curr: (Double, Int), agg: (Double, Int)) => (curr._1 + agg._1, curr._2 + agg._2)
    )
    // ("zhangsan",(99+96+97, 3))
    val resultRDD = combineResult.map(item => (item._1, item._2._1 / item._2._2))
    // 3.获取结果，打印结果
    resultRDD.collect().foreach(println(_))

  }

  /**
   * foldByKey和Spark中reduceByKey的区别是，可以指定初始值
   */
  @Test
  def foldByKey():Unit = {
    sc.parallelize(Seq(("a",1),("a",1),("b",1)))
      .foldByKey(10)((curr,agg) => curr + agg)
      .collect()
      .foreach(println(_))
  }

  /**
   * aggregateByKey(zeroValue)(seqOp,combOp)
   * zeroValue指定初始值
   * seqOp:作用于每一个元素，根据初始值进行计算
   * combOp:将seqOp处理的结果进行聚合
   *
   * aggregateByKey特别适合每个数据要先处理 再聚合的场景
   */
  @Test
  def aggregateByKey(): Unit = {
    val rdd = sc.parallelize(Seq(("手机",10.0),("手机",15.0),("电脑",20.0)))
    rdd.aggregateByKey(0.8)((zeroValue,item) => item*zeroValue, (curr,agg)=> curr+agg)
      .collect()
      .foreach(println(_))
  }

  /**
   * join 笛卡尔积
   */
  @Test
  def join():Unit = {
    var rdd1 = sc.parallelize(Seq(("a",1),("a",2),("b",1)))
    var rdd2 = sc.parallelize(Seq(("a",10),("a",11),("a",12)))
    rdd1.join(rdd2)
      .collect()
      .foreach(println(_))
  }

  /**
   * sortBy可以作用于任何数据类型的RDD，sortByKey 只有kv类型数据才有
   */
  @Test
  def sort():Unit={
    var rdd1 = sc.parallelize(Seq(2,4,1,5,1,6))
    var rdd2 = sc.parallelize(Seq(("a",1),("b",3),("c",2)))
    rdd1.sortBy(item => item)

    rdd2.sortBy(item => item._2)
    rdd2.sortByKey().collect().foreach(println(_))
  }

  /**
   * repartition 进行重分区，默认是Shuffle的
   * coalesce 进行重分区，默认是不Shuffle的，默认不能增大分区数
   */
  @Test
  def partition():Unit = {
    var rdd = sc.parallelize(Seq(1,2,3,4,5),2)

    // repartition
    //println(rdd.repartition(5).partitions.size)
    //println(rdd.repartition(1).partitions.size)

    // coalesce
    //println(rdd.coalesce(1).partitions.size)
    //println(rdd.coalesce(5).partitions.size)
    println(rdd.coalesce(5, shuffle = true).partitions.size)
  }

}
