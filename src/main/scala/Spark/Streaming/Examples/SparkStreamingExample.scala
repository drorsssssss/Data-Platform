package Spark.Streaming.Examples

import org.apache.spark._
import org.apache.spark.streaming._

class SparkStreamingExample {

  def process() = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(1))

    val lines = ssc.socketTextStream("localhost", 4444)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)

    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()
  }

}


object SparkStreamingExample {
  def main(args: Array[String]): Unit = {
    val inst = new SparkStreamingExample
    inst.process()

  }



}
