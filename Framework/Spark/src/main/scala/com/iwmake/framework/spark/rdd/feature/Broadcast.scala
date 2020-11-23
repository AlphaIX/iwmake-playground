package com.iwmake.framework.spark.rdd.feature

import org.apache.spark.{SparkConf, SparkContext}
import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-22
 */
class Broadcast {

  /**
   * 资源占用比较大， 有多少个item(Task)就要拷贝多少个v
   */
  @Test
  def bc1(): Unit = {
    // 假设这个数据很大，100M
    var v = Map("Spark" -> "http://spark.apache.org", "Scala" -> "http://www.scala.lang.org")

    var conf = new SparkConf().setAppName("sc").setMaster("local[6]")
    var sc = new SparkContext(conf)

    // 将其中的Spark 和Scala转为对应的网址
    var r = sc.parallelize(Seq("Spark", "Scala"))
    var result = r.map(item => v(item)).collect()

    result.foreach(println(_))

  }

  /**
   * 使用广播，大幅减少value的复制
   */
  @Test
  def bc2(): Unit = {
    // 假设这个数据很大，100M
    var v = Map("Spark" -> "http://spark.apache.org", "Scala" -> "http://www.scala.lang.org")

    var conf = new SparkConf().setAppName("sc").setMaster("local[6]")
    var sc = new SparkContext(conf)

    // 创建广播
    var bc = sc.broadcast(v)

    // 将其中的Spark 和Scala转为对应的网址
    var r = sc.parallelize(Seq("Spark", "Scala"))
    // 在算子中使用广播变量代替直接引用集合，只会复制和Executor一样的数量，而不是task数量
    var result = r.map(item => bc.value(item)).collect()

    result.foreach(println(_))

  }


}
