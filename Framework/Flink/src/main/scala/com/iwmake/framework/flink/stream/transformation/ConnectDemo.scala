package com.iwmake.framework.flink.stream.transformation

import java.util.concurrent.TimeUnit

import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.{ConnectedStreams, DataStream, StreamExecutionEnvironment}

/**
 * 演示Connect使用
 * 把两个数据流连接到一起
 *
 * @author Dylan
 * @since 2020-12-02
 */
object ConnectDemo {

  def main(args: Array[String]): Unit = {
    // 1 创建StreamEnv
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // 2 加载Source
    import org.apache.flink.api.scala._
    val numDS: DataStream[Long] = env.addSource(new MyNumberSource)
    val strDS: DataStream[String] = env.addSource(new MyStrSource)

    // 3 使用connect连接
    val connectDS: ConnectedStreams[Long, String] = numDS.connect(strDS)

    val resDS: DataStream[String] = connectDS.map(l => "long" + l, s => "string" + s)
    // connect 的意义在哪里？只是把两个流合并为一个，但是处理业务逻辑还是按照自己的方法处理(map需要2个func)?
    // connect之后两条流可以共享状态数据

    resDS.print()
    // 启动
    env.execute()
  }

}

// 自定义产生自增的数字 数据源 第一个数据源
class MyNumberSource extends SourceFunction[Long] {
  var isRunning = true
  var num = 1L

  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    while (isRunning) {
      num += 1
      ctx.collect(num)
      TimeUnit.SECONDS.sleep(1)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
  }
}

// 第二个数据源
class MyStrSource extends SourceFunction[String] {
  var isRunning = true
  var num = 1L

  override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
    while (isRunning) {
      num += 1
      ctx.collect("str" + num)
      TimeUnit.SECONDS.sleep(1)
    }
  }

  override def cancel(): Unit = {
    isRunning = false
  }
}
