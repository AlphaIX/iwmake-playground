package com.iwmake.framework.flink.stream.source.custom

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import java.util.concurrent.TimeUnit

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
 * 演示自定义 Rich并行数据源
 *
 * @author Dylan
 * @since 2020-12-02
 */
// 定义student样例类
case class Student(id: Int, name: String, age: Int)

object MySQLRichParallelSource {
  def main(args: Array[String]): Unit = {
    // 1.创建流处理运行环境
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // 2. 添加自定义数据源
    import org.apache.flink.api.scala._
    val stuDS: DataStream[Student] = env.addSource(new MySQLRichParallelSourceFunction)
      .setParallelism(1) // 设置并行度

    // 打印数据
    stuDS.print()

    // 启动
    env.execute()
  }
}

// 自定义MySQL并行数据源
class MySQLRichParallelSourceFunction extends RichParallelSourceFunction[Student] {
  var isRunning = true
  var connection: Connection = null
  var ps: PreparedStatement = null

  // 开启MySQL连接
  override def open(parameters: Configuration): Unit = {
    // 驱动方式
    connection = DriverManager.getConnection("jdbc:mysql://node03:3306/test", "root", "123456")
    // 准备sql语句查询所有数据
    var sql = "select id,name,age from t_student"
    ps = connection.prepareStatement(sql)
  }

  // 关闭释放资源
  override def close(): Unit = {
    if (connection != null) {
      connection.close()
    }
    if (ps != null) {
      ps.close()
    }
  }

  // 读取MySQL数据
  override def run(ctx: SourceFunction.SourceContext[Student]): Unit = {
    while (isRunning) {
      val result: ResultSet = ps.executeQuery()
      while (result.next()) {
        val userId = result.getInt("id")
        val name = result.getString("name")
        val age = result.getInt("age")
        // 收集并发送数据
        ctx.collect(Student(userId, name, age))
      }
      // 每5秒执行一次
      TimeUnit.SECONDS.sleep(5)
    }
  }

  // 取消
  override def cancel(): Unit = {
    isRunning = false
  }
}

