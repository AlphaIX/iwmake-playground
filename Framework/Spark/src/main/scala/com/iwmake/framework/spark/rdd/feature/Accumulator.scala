package com.iwmake.framework.spark.rdd.feature

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.util.AccumulatorV2
import org.junit.Test

import scala.collection.mutable

/**
 * @author Dylan
 * @since 2020-11-22
 */
class Accumulator {

  @Test
  def acc(): Unit = {
    var conf = new SparkConf().setAppName("acc").setMaster("local[6]")
    var sc = new SparkContext(conf)

    var numAcc = new NumAccumulator()
    // 注册给spark
    sc.register(numAcc, "num")

    sc.parallelize(Seq("1", "2", "3"))
      .foreach(item => numAcc.add(item))

    println(numAcc.value)

    sc.stop()
  }
}

class NumAccumulator extends AccumulatorV2[String, Set[String]] {
  private val nums: mutable.Set[String] = mutable.Set()

  /**
   * 告诉spark框架这个累加器对象是否是空的
   *
   * @return
   */
  override def isZero: Boolean = {
    nums.isEmpty
  }

  /**
   * 提供给spark框架一个拷贝的累加器
   *
   * @return
   */
  override def copy(): AccumulatorV2[String, Set[String]] = {
    var newAccumulator = new NumAccumulator()
    nums.synchronized {
      newAccumulator.nums ++= this.nums
    }
    newAccumulator
  }

  /**
   * 帮助spark框架，清理累加器的内容
   */
  override def reset(): Unit = {
    nums.clear()
  }

  /**
   * 外部传入要累加的内容，在这个方法中进行累加
   *
   * @param v
   */
  override def add(v: String): Unit = {
    nums += v
  }

  /**
   * 累加器在进行累加的时候，可能在每个分布式节点都有一个实例
   * 在Driver最后进行一次合并
   *
   * @param other
   */
  override def merge(other: AccumulatorV2[String, Set[String]]): Unit = {
    nums ++= other.value
  }

  /**
   * 提供给外部累加结果
   * 为什么一定要不可变的，因为外部可能会修改，而这不是我们想要的
   *
   * @return
   */
  override def value: Set[String] = {
    nums.toSet
  }
}
