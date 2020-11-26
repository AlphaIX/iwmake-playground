package com.iwmake.framework.spark.structuredstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.OutputMode
import org.apache.spark.sql.types.StructType

/**
 * @author Dylan
 * @since 2020-11-26
 */
object HDFSSource {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .master("local[6]")
      .appName("hdfs_source")
      .getOrCreate()

    // 读取数据，path只能是文件夹
    val schema = new StructType()
        .add("name", "string")
        .add("age", "integer")

    val source = spark.readStream
      .schema(schema)
      .json("hdfs://node01:8020/dataset/dataset")

    source.writeStream
      .outputMode(OutputMode.Append())
      .format("console")
      .start()
      .awaitTermination()
  }
}
