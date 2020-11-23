package com.iwmake.framework.spark.sql

import org.apache.spark.sql.{DataFrameReader, SaveMode, SparkSession}
import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-23
 */
class ReadWrite {

  val spark = SparkSession.builder()
    .master("local[6]")
    .appName("reader1")
    .getOrCreate()

  @Test
  def read1():Unit = {
    // 创建spark session
    val spark = SparkSession.builder()
      .master("local[6]")
      .appName("reader1")
      .getOrCreate()

    // 框架在哪
    val reader: DataFrameReader = spark.read

  }

  /**
   * Spark读取数据方式
   */
  @Test
  def read2():Unit = {
    // 创建spark session
    val spark = SparkSession.builder()
      .master("local[6]")
      .appName("reader1")
      .getOrCreate()

    // 第一种形式
    spark.read
      .format("csv")
      .option("header",value = false)
      .option("inferSchema", value = true)
      .load("dataset/input/beijing_pm_data.csv")
      .show(10)


    // 第二种形式
    spark.read
      .option("header",value = false)
      .option("inferSchema", value = true)
      .csv("dataset/input/beijing_pm_data.csv")
      .show()

  }

  @Test
  def write1():Unit = {
    // 创建spark session
    val spark = SparkSession.builder()
      .master("local[6]")
      .appName("reader1")
      .getOrCreate()

    // 读取数据集
    val df = spark.read
      .option("header", value = false)
      .csv("dataset/input/beijing_pm_data.csv")

    // 写入数据集
    df.write.json("dataset/output/beijing_pm.json")

    df.write.format("json").save("dataset/output/beijing_pm2.json")


  }

  @Test
  def parquet(): Unit ={
    // 读取CSV文件的数据
    val df = spark.read
      .option("header", value = false)
      .csv("dataset/input/beijing_pm_data.csv")

    // 把数据写为Parquet格式
    df.write
      //.format("parquet") //写入的时候 默认就是Parquet
      .mode(SaveMode.Overwrite)
      .save("dataset/output/beijing_pm3")

    // 读取Parquet格式文件
    // 默认是否是parquet？ 是
    // 默认是否能读取文件夹？ 是
    spark.read
      .load("dataset/output/beijing_pm3")
      .show()
  }

  /**
   * 表分区的概念不仅在parquet上有，其他格式的文件也可以指定表分区
   */
  @Test
  def parquetPartitions(): Unit = {
    // 读取文件
    val df = spark.read
      .option("header", value = false)
      .csv("dataset/input/beijing_pm_data.csv")

    // 写文件，表分区
    df.write
      .partitionBy("_c0")
      .save("dataset/output/beijing_pm4")

    // 读文件，自动发现分区
    spark.read
      .parquet("dataset/output/beijing_pm4")
  }

}
