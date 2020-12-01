package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * FullOuterJoin演示
 *
 * @author Dylan
 * @since 2020-11-30
 */

object FullOuterJoinDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    // 用户数据
    val userDS: DataSet[(Int, String)] = env.fromCollection(List((1,"zhangsan"),(2,"lisi"),(3,"wangwu")))
    // 城市数据
    val cityDS: DataSet[(Int, String)] = env.fromCollection(List((1,"beijing"),(2,"shanghai"),(4,"guangzhou")))


    // 3 转换操作 fullOuterJoin
    val fullJoinAssigner: JoinFunctionAssigner[(Int, String), (Int, String)] = userDS.fullOuterJoin(cityDS).where(0).equalTo(0)
    // 3.1 使用apply方法解析JoinFunctionAssigner中的数据处理逻辑
    val resDS: DataSet[(Int, String, String)] = fullJoinAssigner.apply((left, right) => {
      if (right == null) {
        // 返回数据
        (left._1, left._2, "null")
      }else if(left == null){
        (right._1,right._2,"null")
      } else {
        //返回
        (left._1, left._2, right._2)
      }
    })

    // 4 sink 输出
    resDS.print()


    // 5 执行



  }

}
