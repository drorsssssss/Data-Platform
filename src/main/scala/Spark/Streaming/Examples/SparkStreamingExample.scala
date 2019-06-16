package Spark.Streaming.Examples

import org.apache.spark._
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.DStream

class SparkStreamingExample {

  def process(conf:SparkConf, ssc:StreamingContext) = {

    val lines = ssc.socketTextStream("localhost", 4444)
    val words = lines.flatMap(_.split(" "))

    words.foreachRDD(rdd=>{
      val spark = SparkSession.builder().config(rdd.sparkContext.getConf).getOrCreate()
      import spark.implicits._

      val df = rdd.toDF("word")
      df.createOrReplaceTempView("words")

      val dfCount = spark.sql("select word,count(1) from words group by word")
      dfCount.show()


    })

    ssc.start()
    ssc.awaitTermination()
  }

}


object SparkStreamingExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(1))

    val inst = new SparkStreamingExample
    inst.process(conf,ssc)

  }



}
