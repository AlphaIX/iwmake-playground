package com.iwmake.framework.spark.rdd.feature

import org.junit.Test

/**
 * @author Dylan
 * @since 2020-11-22
 */
class Closure {

  /**
   * 编写一个高价函数，在这个函数内要有一个变量
   * 返回一个函数，通过这个变量完成一个计算
   */
  @Test
  def test(): Unit = {
//    var f: Int => Double = closure()
//    var area = f(5)
//    println(area)

    // test中不能直接访问factor变量
    // 在拿到f的时候，通过f间接访问到closure作用域的内容，
    // 说明f携带了一个作用域
    // 如果一个函数携带了一个外部的作用域，这种函数我们称之为闭包
    var f = closure()
    f(5)

    // 闭包的本质是什么--->一个函数
    // 在Scala中函数也是一个特殊类型，FunctionX
    // 闭包也是一个FunctionX类型的对象，闭包是--->一个对象


  }

  /**
   * 返回一个新的函数
   */
  def closure(): Int => Double = {
    var factor = 3.14
    var areaFunction = (r: Int) => math.pow(r, 2) * factor
    areaFunction
  }

}
