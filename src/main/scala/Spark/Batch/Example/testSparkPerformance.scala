package Spark.Batch.Example

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions._



object testSparkPerformance {
  def main(args: Array[String]): Unit = {

    var conf = new SparkConf()
    conf.setMaster("local[*]")
    conf.setAppName("a")
    conf.set("spark.eventLog.enabled","true")
    conf.set("spark.executor.memory","3g")
    conf.set("spark.executor.cores","1")
    conf.set("spark.driver.cores","4")
    conf.set("spark.driver.memory","3g")

    val spark  =
      SparkSession
        .builder
        .config(conf)
        .getOrCreate()

    //val df = spark.read.format("csv").option("header","true").load("/Users/dsivan/GitRepos/Data-Platform/src/test/resources/SampleData/")
    val schema = StructType(List(StructField("name",StringType,nullable = true),StructField("country",StringType,nullable = true),StructField("age",IntegerType,nullable = true)))
    val data = Seq(Row("dror","Israel",30),Row("sivan","Israel",30),Row("dor","Israel",32),Row("mor","Israel",16),Row("shay","Israel",35),Row("pitz","Israel",21))

    val schemaCountires = StructType(List(StructField("country_name",StringType,nullable = true),StructField("country_pop",IntegerType,nullable = true)))
    val dataCountries = Seq(Row("Israel",300),Row("China",30000000),Row("USA",10000),Row("England",500),Row("Swiss",100),Row("France",200))

    val df = spark.createDataFrame(spark.sparkContext.parallelize(data),schema)
    val dfCountries = spark.createDataFrame(spark.sparkContext.parallelize(dataCountries),schemaCountires)

    import spark.implicits._
    val dfJoined = df.join(dfCountries,$"country" === $"country_name","left")
    dfJoined.show











  }

}
