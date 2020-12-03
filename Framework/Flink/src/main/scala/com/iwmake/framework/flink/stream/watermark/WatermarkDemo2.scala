package com.iwmake.framework.flink.stream.watermark

import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, WindowedStream}
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

/**
 * 手动方式实现watermark
 *
 * @author Dylan
 * @since 2020-12-03
 */
// 3. 定义 样例类
case class CarWc(id: String, num: Int, ts: Long)

object WatermarkDemo2 {
  def main(args: Array[String]): Unit = {
    // 1 创建运行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // 2.1 设置处理时间为事件事件
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    // 2.2 生成水印的周期 默认200ms
    env.getConfig.setAutoWatermarkInterval(200)

    // 默认程序并行度是集群的核数，注意在flink中如果是多个并行度，水印时间是每个并行度比较最小的值作为当前流的watermark
    env.setParallelism(1)

    // 4. 添加Socket Source
    val socketDS: DataStream[String] = env.socketTextStream("node01", 9999)
    // 5. 数据处理之后添加水印
    import org.apache.flink.api.scala._
    val carWcDS: DataStream[CarWc] = socketDS.map(line => {
      val arr: Array[String] = line.split(",")
      CarWc(arr(0), arr(1).trim.toInt, arr(2).trim.toLong)
    })
    // 5.2添加水印 周期性 new AssignerWithPeriodicWatermarks
    val watermarkDS: DataStream[CarWc] = carWcDS.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[CarWc] {
      // watermark = eventtime-延迟
      // 5.2.1 定义允许延迟的时间 2s
      val delayTime = 2000

      var currentMaxTimestamp = 0L
      var lastEmittedWatermark: Long = Long.MinValue

      //  获取watermark时间
      override def getCurrentWatermark: Watermark = {
        // 计算watermark
        val watermarkTime: Long = currentMaxTimestamp - delayTime
        if (watermarkTime > lastEmittedWatermark) {
          lastEmittedWatermark = watermarkTime
        }
        new Watermark(lastEmittedWatermark)
      }

      // 抽取时间戳 element新到达的元素  previousElementTimestamp之前元素最大的时间戳
      // 5.2.2 抽取时间戳 计算watermark
      override def extractTimestamp(element: CarWc, previousElementTimestamp: Long): Long = {
        // 获取到时间
        // 注意问题：时间倒退问题，消息过来是乱序的，每次新来的消息时间戳不一定是递增的，所以可能导致水印倒退
        val eventTime = element.ts
        if (eventTime > currentMaxTimestamp) { // 以之前最大的时间戳比较
          currentMaxTimestamp = eventTime
        }
        eventTime
      }
    })

    // 6 设置窗口 5s的滚动窗口
    val windowDS: WindowedStream[CarWc, Tuple, TimeWindow] = watermarkDS.keyBy(0).window(TumblingEventTimeWindows.of(Time.seconds(5)))

    // 7 使用apply方法对窗口进行计算
    val resDS: DataStream[CarWc] = windowDS.apply(
      // 泛型 1carwc 2carwc 3tuple 4timewindow
      new WindowFunction[CarWc, CarWc, Tuple, TimeWindow] {
        override def apply(key: Tuple, window: TimeWindow, input: Iterable[CarWc], out: Collector[CarWc]): Unit = {
          val wc = input.reduce(
            (c1, c2) => CarWc(c1.id, c1.num + c2.num, c2.ts) //累加出通过的汽车数量，关于时间这里我们不关系

          )
          // 发送计算结果
          out.collect(wc)
          // 获取到窗口开始和结束时间
          println("窗口开始时间>>" + window.getStart + " || 窗口结束时间>>" + window.getEnd + " || 窗口中的数据>>" + input.iterator.mkString(","))

        }
      }
    )
    // 打印结果
    resDS.print()
    // 启动
    env.execute()

  }
}
