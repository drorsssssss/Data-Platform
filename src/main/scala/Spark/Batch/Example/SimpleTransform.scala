package Spark.Batch.Example

import org.apache.spark.sql.SparkSession


object SimpleTransform {


  def main(args: Array[String]): Unit = {
    val spark  =
    SparkSession
      .builder
      .appName("Test")
      .config("spark.eventLog.enabled",true)
      .master("local[*]")
      .getOrCreate()



    val df = spark.read.format("text").option("headers","true").load("/Users/dsivan/GitRepos/Data-Platform/src/test/resources/SampleData/")
    df.show(10)

  }
}
