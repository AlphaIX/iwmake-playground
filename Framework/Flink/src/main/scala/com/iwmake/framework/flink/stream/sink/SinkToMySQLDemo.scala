package com.iwmake.framework.flink.stream.sink

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}

/**
 * flink程序 计算结果保存到MySQL中
 * 需要自己实现MySQL sink function
 *
 * @author Dylan
 * @since 2020-12-02
 */
// 定义student样例类
case class Student(id: Int, name: String, age: Int)

object SinkToMySQLDemo {
  def main(args: Array[String]): Unit = {
    // 1. 创建流处理运行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 2. 加载source
    import org.apache.flink.api.scala._
    val stuDS: DataStream[Student] = env.fromElements(Student(1, "tony", 18))

    // 3. 写出到MySQL
    stuDS.addSink(new MySQLSinkFunction)

    // 4.执行
    env.execute()

  }
}

class MySQLSinkFunction extends RichSinkFunction[Student] {
  var connection: Connection = null
  var ps: PreparedStatement = null

  // 3.1打开连接
  override def open(parameters: Configuration): Unit = {
    // 3.1.1驱动方式
    connection = DriverManager.getConnection("jdbc:mysql://node03:3306/test", "root", "123456")
    // 3.1.2准备sql语句 插入数据
    val sql = "insert into t_student(name,age) values (?,?)"
    ps = connection.prepareStatement(sql)
  }

  // 关闭连接
  override def close(): Unit = {
    if (connection != null) {
      connection.close()
    }
    if (ps != null) {
      ps.close()
    }
  }

  // 3.2负责写入数据导MySQL
  override def invoke(value: Student, context: SinkFunction.Context[_]): Unit = {
    // 3.2.1设置参数
    ps.setString(1,value.name)
    ps.setInt(2,value.age)
    // 3.2.2插入数据
    ps.executeUpdate()
  }
}
