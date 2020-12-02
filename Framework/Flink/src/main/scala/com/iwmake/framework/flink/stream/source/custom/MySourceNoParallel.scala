package com.iwmake.framework.flink.stream.source.custom

import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
 * 演示自定义非并行数据源
 *
 * @author Dylan
 * @since 2020-12-02
 */
object MySourceNoParallel {
  def main(args: Array[String]): Unit = {
    // 1.创建流处理运行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // 2. 添加自定义数据源
    import org.apache.flink.api.scala._
    val myDS: DataStream[Long] = env.addSource(new MyNoParallelSourceFunction)
        //.setParallelism(2) 报错

    // 打印数据
    myDS.print()

    // 启动
    env.execute()
  }
}

// SourceFunction泛型是我们自定义source返回的数据类型
class MyNoParallelSourceFunction extends SourceFunction[Long] {
  var ele: Long = 0
  var isRunning = true
  // 发送数据，生成数据的方法
  override def run(ctx: SourceFunction.SourceContext[Long]): Unit = {
    while (isRunning){
      ele +=1
      // 通过上下文对象发送数据
      ctx.collect(ele)
      // 每一秒发送一次
      Thread.sleep(1000)
    }
  }

  // 取消方法，取消是通过控制一个变量来影响run方法的while循环
  override def cancel(): Unit = {
    isRunning = false
  }
}
