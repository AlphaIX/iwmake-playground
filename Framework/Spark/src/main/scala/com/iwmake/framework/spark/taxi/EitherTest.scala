package com.iwmake.framework.spark.taxi

/**
 * @author Dylan
 * @since 2020-11-24
 */
object EitherTest {

  def main(args: Array[String]): Unit = {
    def process(b: Double) = {
      val a = 10.0
      a / b
    }

    def safe(f: Double => Double, b: Double): Either[Double, (Double, Exception)] = {
      try {
        val result = f(b)
        Left(result)
      } catch {
        case e: Exception => Right(b, e)
      }
    }

    //process(0.0)
    val result = safe(process,0)
    result match {
      case Left(r) => println(r)
      case Right((b,e)) => println(b,e)
    }
  }

}
