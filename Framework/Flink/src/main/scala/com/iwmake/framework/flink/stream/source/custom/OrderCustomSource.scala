package com.iwmake.framework.flink.stream.source.custom

import java.util.UUID
import java.util.concurrent.TimeUnit

import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

import scala.util.Random

/**
 * 自定义数据源 练习 生成订单数据
 *
 * @author Dylan
 * @since 2020-12-02
 */
// 订单信息(订单ID，用户ID，订单金额，时间戳)
case class Order(id: String, userId: Int, money: Long, createTime: Long)

object OrderCustomSource {
  def main(args: Array[String]): Unit = {
    /**
     * 1 创建订单样例类
     * 2 获取流处理环境
     * 3 创建自定义数据源
     *   - 循环1000次
     *   - 随机构建订单信息
     *   - 上下文收集数据
     *   - 每隔一秒执行一次循环
     * 4 打印数据
     * 5 执行任务
     */
    //
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    // 加载自定义数据源
    import org.apache.flink.api.scala._
    val orderDS: DataStream[Order] = env.addSource(new RichParallelSourceFunction[Order] {
      var isRunning = true

      // 生成订单数据方法
      override def run(ctx: SourceFunction.SourceContext[Order]): Unit = {
        while (isRunning) {
          // orderId
          val orderId: String = UUID.randomUUID().toString
          // userId
          val userId: Int = Random.nextInt(3)
          // Money
          val money: Int = Random.nextInt(101)
          // createTime
          val createTIme: Long = System.currentTimeMillis()

          ctx.collect(Order(orderId, userId, money, createTIme))
          TimeUnit.SECONDS.sleep(1)
        }
      }

      // 取消数据生成
      override def cancel(): Unit = {
        isRunning = false
      }
    }).setParallelism(1)

    // 打印数据
    orderDS.print()

    // 启动
    env.execute()
  }
}
