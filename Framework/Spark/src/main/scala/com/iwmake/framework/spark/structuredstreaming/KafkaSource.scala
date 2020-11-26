package com.iwmake.framework.spark.structuredstreaming

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * @author Dylan
 * @since 2020-11-26
 */
object KafkaSource {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[6]")
      .appName("kafka_source")
      .getOrCreate()

    import spark.implicits._
    // 读取kafka数据
    val source: Dataset[String] = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers","node01:9092,node02:9092,node03:9092")
      .option("subscribe","streaming_test_1")
      .option("startingOffsets","earliest")
      .load()
    //source.printSchema()
      .select("CAST(value AS STRING) as value")
      .as[String]


  }
}
