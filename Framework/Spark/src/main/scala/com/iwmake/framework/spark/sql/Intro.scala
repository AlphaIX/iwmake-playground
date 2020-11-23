package com.iwmake.framework.spark.sql

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.catalyst.InternalRow
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.{SparkConf, SparkContext, sql}
import org.junit.Test

/**
 * SparkSQL 入门介绍
 *
 * @author Dylan
 * @since 2020-11-23
 */
class Intro {

  /**
   * RDD方式
   */
  @Test
  def rddIntro(): Unit = {
    val conf = new SparkConf().setMaster("local[6]").setAppName("rdd_intro")
    val sc = new SparkContext(conf)

    sc.textFile("dataset/input/wordcount.txt")
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .collect()
      .foreach(println(_))
  }

  /**
   * SQL方式
   */
  @Test
  def dsIntro(): Unit = {
    val spark = new SparkSession.Builder()
      .appName("ds_intro")
      .master("local[6]")
      .getOrCreate()

    import spark.implicits._

    val sourceRDD = spark.sparkContext.parallelize(Seq(Person("zhangsan", 10), Person("lisi", 15)))

    val personDS: Dataset[Person] = sourceRDD.toDS()

    val resultDS = personDS.where('age > 10)
      .where('age < 20)
      .select('name)
      .as[String]

    resultDS.show()
  }

  @Test
  def dfIntro(): Unit = {
    val spark = new SparkSession.Builder()
      .appName("ds_intro")
      .master("local[6]")
      .getOrCreate()

    import spark.implicits._

    val sourceRDD = spark.sparkContext.parallelize(Seq(Person("zhangsan", 10), Person("lisi", 15)))

    val df = sourceRDD.toDF()
    df.createOrReplaceTempView("person")

    val resultDF = spark.sql("select name from person where age > 10 and age < 20")
    resultDF.show()
  }

  @Test
  def dataset1():Unit = {
    // 1.创建SparkSession
    val spark = new sql.SparkSession.Builder()
      .master("local[6]").appName("dataset1")
      .getOrCreate()

    // 2.导入隐式转换
    import spark.implicits._

    // 3.演示
    val sourceRDD = spark.sparkContext.parallelize(Seq(Person("zhangsan", 10), Person("lisi", 15)))
    val dataset = sourceRDD.toDS()

    // Dataset 支持强类型的API
    dataset.filter(item => item.age > 10).show()
    // Dataset 支持弱类型的API
    dataset.filter('age > 10).show()
    dataset.filter($"age" > 10).show()
    // Dataset 可以直接编写SQL表达式
    dataset.filter("age > 10").show()
  }

  @Test
  def dataset2():Unit = {
    // 1.创建SparkSession
    val spark = new sql.SparkSession.Builder()
      .master("local[6]").appName("dataset1")
      .getOrCreate()

    // 2.导入隐式转换
    import spark.implicits._

    // 3.演示
    val sourceRDD = spark.sparkContext.parallelize(Seq(Person("zhangsan", 10), Person("lisi", 15)))
    val dataset = sourceRDD.toDS()

//    dataset.explain(true)
    // 无论Dataset中放置的是什么类型的对象，最终执行计划中的RDD上都是InternalRow
    val executionRDD: RDD[InternalRow] = dataset.queryExecution.toRdd
  }

  @Test
  def dataset3():Unit = {
    // 1.创建SparkSession
    val spark = new sql.SparkSession.Builder()
      .master("local[6]").appName("dataset1")
      .getOrCreate()

    // 2.导入隐式转换
    import spark.implicits._

    // 3.演示
//    val sourceRDD = spark.sparkContext.parallelize(Seq(Person("zhangsan", 10), Person("lisi", 15)))
//    val dataset = sourceRDD.toDS()
    val dataset = spark.createDataset(Seq(Person("zhangsan", 10), Person("lisi", 15)))

    //    dataset.explain(true)
    // 无论Dataset中放置的是什么类型的对象，最终执行计划中的RDD上都是InternalRow
    // 直接获取已经分析和解析过的Dataset的执行计划，从中拿到RDD
    val executionRDD: RDD[InternalRow] = dataset.queryExecution.toRdd

    // 通过将Dataset底层的RDD[InternalRow]通过Decoder转成了和Dataset一样的类型的RDD
    val typeRDD: RDD[Person] = dataset.rdd

    println(executionRDD.toDebugString)
    println()
    println()
    println(typeRDD.toDebugString)
  }



  @Test
  def dataframe1(): Unit = {
    // 1.创建SparkSession
    val spark = SparkSession.builder().master("local[6]").appName("dataframe1")
      .getOrCreate()

    // 2.创建DataFrame
    import spark.implicits._

    var dataframe: DataFrame = Seq(Person("zhangsan",15),Person("lissi",20)).toDF()

    // 3.看看DataFrame的常见操作
    // select name from ...t where ... t.age > 10
    dataframe.where('age>10)
      .select('name)
      .show()
  }

  @Test
  def dataframe2(): Unit = {
    // 1.创建SparkSession
    val spark = SparkSession.builder()
      .master("local[6]")
      .appName("dataframe1")
      .getOrCreate()

    // 2.创建DataFrame
    import spark.implicits._

    val personList = Seq(Person("zhangsan",15),Person("lissi",20))

    // 创建DataFrame三种方式
    // 1. toDF
    val df1 = personList.toDF()
    val df2 = spark.sparkContext.parallelize(personList).toDF()

    // 2. createDataFrame
    val df3 = spark.createDataFrame(personList)

    // 3. DataFrameReader
    val df4 =spark.read.csv("dataset/input/beijing_pm_data.csv")
    df4.show()
  }
}

case class Person(name: String, age: Int)
