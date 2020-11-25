package com.iwmake.framework.spark.taxi

import org.json4s.jackson.Serialization

/**
 * @author Dylan
 * @since 2020-11-25
 */
object JsonTest {

  def main(args: Array[String]): Unit = {
    import org.json4s._
    import org.json4s.jackson.JsonMethods._
    import org.json4s.jackson.Serialization.{read, write}

    val product =
      """
        |{"name":"Toy","price":35.35}
        |""".stripMargin

    // 隐式转换的形式提供格式工具，例如如何解析时间字符串
    implicit val formats = Serialization.formats(NoTypeHints)

    // 具体解析为某一个对象
    val productObj1 = parse(product).extract[Product]

    // 可以通过read方法，直接将json字符串转为对象，但是这种方式就无法进行搜索了
    val productObj2 = read[Product](product)

    // 将对象转为JSON字符串
    val productObj3 = Product("电视",10.5)
    //val jsonStr1 = compact(render(productObj3))
    val jsonStr = write(productObj3)

    println(jsonStr)
  }

}

case class Product(name: String, price: Double) {

}
