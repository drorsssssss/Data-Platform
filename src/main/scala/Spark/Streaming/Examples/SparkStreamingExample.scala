package Spark.Streaming.Examples

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.dstream.DStream

class SparkStreamingExample {

  def process(conf:SparkConf, ssc:StreamingContext) = {

    val lines = ssc.socketTextStream("localhost", 4444)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts: DStream[(String, Int)] = pairs.reduceByKeyAndWindow((a:Int, b:Int) => (a + b), Seconds(30), Seconds(2))

    wordCounts.print()

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
