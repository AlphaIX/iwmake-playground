import org.apache.spark.{SparkConf, SparkContext}

object WordCount{
  def main(args: Array[String]): Unit = {
    // 1.创建SparkContext
    var conf = new SparkConf().setAppName("word_count").setMaster("local[6]")
    var sc = new SparkContext(conf)

    // 2.加载数据文件
    // 2.1准备文件 工程根目录下dataset
    // 2.2读取文件
    var rdd1 = sc.textFile("dataset/input/spark/wordcount.txt")// 相对路径

    // 3.处理
    // 3.1拆分为多个单词
    val rdd2 = rdd1.flatMap(item => item.split(" "))
    // 3.2把每个单词指定一个词频1
    var rdd3 = rdd2.map(item => (item, 1))
    // 3.3聚合
    var rdd4 = rdd3.reduceByKey((curr, agg) => curr + agg)

    // 4.得到结果
    var result = rdd4.collect()
    result.foreach(item => println(item))
  }
}