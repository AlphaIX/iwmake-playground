package com.iwmake.framework.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author Dylan
 * @since 2020-11-26
 */
object StreamingWordCount {

  def main(args: Array[String]): Unit = {
    // 1.初始化环境
    val conf = new SparkConf()
      .setAppName("streaming_word_count")
      // 指定master的时候不能只指定一个线程，因为Streaming在运行时，需要开启一个线程去监听数据获取
      .setMaster("local[6]")
    val ssc = new StreamingContext(conf, Seconds(5))
    ssc.sparkContext.setLogLevel("WARN")

    //socketTextStream用于创建一个DStream，监听Socket输入，当作文本来处理
    val lines: ReceiverInputDStream[String] = ssc.socketTextStream(
      hostname = "192.168.56.120",
      port = 9999,
      storageLevel = StorageLevel.MEMORY_AND_DISK_SER
    )

    //lines.flatMap(_.split(" ")).foreachRDD(item => println(item))

    // 2.数据处理
    // 2.1 把句子拆为单词
    val words = lines.flatMap(_.split(" "))
    // 2.2 转换单词
    val tuples = words.map((_, 1))
    // 2.3 词频reduce
    val counts = tuples.reduceByKey(_ + _)

    // 3.展示和启动
    counts.print()

    ssc.start()

    // main方法执行完毕后，整个程序就好退出，所以需要阻塞主线程
    ssc.awaitTermination()
  }

}
