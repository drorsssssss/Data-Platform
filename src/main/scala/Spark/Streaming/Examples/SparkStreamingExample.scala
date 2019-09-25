package Spark.Streaming.Examples

import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming._

class SparkStreamingExample {

  def process(conf:SparkConf, ssc:StreamingContext,rddOut:RDD[(String,String)]) = {

    val lines = ssc.socketTextStream("localhost", 4444)
    val words = lines.flatMap(_.split(" "))

    words.foreachRDD(rdd=>{
      val spark = SparkSession.builder().config(rdd.sparkContext.getConf).getOrCreate()
      import spark.implicits._

      val df = rdd.toDF("word")
      df.createOrReplaceTempView("words")

      val dim = rddOut.toDF("id","name")
      dim.createOrReplaceTempView("dim")

      val dfCount = spark.sql("select word,name from words as w left join dim as d on w.word=d.id")
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

    val statDF = ssc.sparkContext.parallelize(Seq(("1","dror"),("1","nitzan"),("3","mango")))

    val inst = new SparkStreamingExample
    inst.process(conf,ssc,statDF)

  }



}
