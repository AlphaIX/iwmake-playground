package com.iwmake.framework.flink.batch.transformation

import org.apache.flink.api.scala.ExecutionEnvironment

/**
 * Join演示
 *
 * @author Dylan
 * @since 2020-11-30
 */

// 定义case class
case class Score(id:Int,name:String,subjectId:Int,score:Double)
case class Subject(id:Int,name:String)

object JoinDemo {

  def main(args: Array[String]): Unit = {
    // 1 env
    val env = ExecutionEnvironment.getExecutionEnvironment

    // 2 source加载数据
    import org.apache.flink.api.scala._
    val scoreDS: DataSet[Score] = env.readCsvFile("0_dataset/input/flink/score.csv")
    val subjectDS: DataSet[Subject] = env.readCsvFile("0_dataset/input/flink/subject.csv")

    // 3 转换操作 Join
    val joinDS: JoinDataSet[Score, Subject] = scoreDS.join(subjectDS).where(2).equalTo(0)

    // 4 sink 输出
    joinDS.print()


    // 5 执行



  }

}
