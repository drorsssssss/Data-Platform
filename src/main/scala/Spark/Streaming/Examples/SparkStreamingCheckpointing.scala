package Spark.Streaming.Examples

import java.io.File

import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.rdd.RDD
import com.google.common.io.Files
import java.nio.charset.Charset

import org.apache.spark.streaming.dstream.DStream

object SparkStreamingCheckpointing{

  def createContext(ip:String,port:Int,outputPath:String,checkpointDirectory: String):StreamingContext={
    println("Creating new context")
    val outputFile = new File(outputPath)
    if (outputFile.exists()) outputFile.delete()
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("RecoverableNetworkWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    ssc.checkpoint(checkpointDirectory)

    val lines = ssc.socketTextStream(ip, port)
    val words = lines.flatMap(_.split(" "))
    val wordCounts: DStream[(String, Int)] = words.map((_, 1)).reduceByKey(_ + _)

    wordCounts.foreachRDD { (rdd: RDD[(String, Int)], time: Time) =>{
      println(s"Appending to ${outputFile.getAbsolutePath}")
      Files.append(rdd.collect().mkString("-") + "\n", outputFile, Charset.defaultCharset())

    }

    }
    ssc

  }



  def main(args: Array[String]): Unit = {
    val checkpointPath = "/Users/dsivan/GitRepos/Data-Platform/src/test/resources/Output/Checkpoints/"
    val outputPath="/Users/dsivan/GitRepos/Data-Platform/src/test/resources/Output/Results"
    val ssc = StreamingContext.getOrCreate(checkpointPath,()=>createContext("localhost",4444,outputPath,checkpointPath))
    ssc.start()
    ssc.awaitTermination()

  }


}
