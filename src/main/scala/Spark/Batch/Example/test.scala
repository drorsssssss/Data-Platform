package Spark.Batch.Example


import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

import scala.collection.mutable


object test {


  def main(args: Array[String]): Unit = {


    val spark  =
      SparkSession
        .builder
        .appName("Test")
        .config("spark.eventLog.enabled",true)
        .master("local[*]")
        .getOrCreate()

    val df = spark.read.format("csv").option("header","true").load("/Users/dsivan/GitRepos/Data-Platform/src/test/resources/SampleData/")
    df.show
    println(df.rdd.partitions.size)
    val res = df.groupBy(col("county")).count()

    val res2 = res.filter("county == 'GULF COUNTY'")
    res2.show
    println(res2.rdd.partitions.size)

    import scala.collection.mutable.HashMap
    import scala.collection.mutable.Map

    var hm = new HashMap[Int,String]()
    hm(1)="3"



  }

}
