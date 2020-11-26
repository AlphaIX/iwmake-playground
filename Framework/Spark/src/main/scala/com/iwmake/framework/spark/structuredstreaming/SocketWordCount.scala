package com.iwmake.framework.spark.structuredstreaming

import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

/**
 * @author Dylan
 * @since 2020-11-26
 */
object SocketWordCount {

  def main(args: Array[String]): Unit = {
    // 1.创建SparkSession
    val spark = SparkSession.builder()
      .master("local[6]")
      .appName("socket_structured_streaming")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")
    import spark.implicits._

    // 2.数据集的生成，数据读取
    val source: DataFrame = spark.readStream
      .format("socket")
      .option("host","192.168.56.120")
      .option("port",9999)
      .load()

    val sourceDS: Dataset[String] = source.as[String]

    // 3.数据的处理
    val words = sourceDS.flatMap(_.split(" "))
      .map((_,1))
      .groupByKey(_._1)
      .count()

    // 4.结果生成和输出
    words.writeStream
      .outputMode(OutputMode.Complete())
      .format("console")
      .start()
      .awaitTermination()
  }
}
