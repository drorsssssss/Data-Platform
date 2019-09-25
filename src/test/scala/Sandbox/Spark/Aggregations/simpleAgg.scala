//package Sandbox.Spark.Aggregations
//
//import Config.ConfigUtil._
//import org.apache.spark.sql.functions._
//
//
//object simpleAgg extends helpers{
//
//  def main(args: Array[String]): Unit = {
//
//
//
//
//    val df = spark.read.json("/Users/dsivan/GitRepos/Data-Platform/src/test/resources/Input")
//    val new_df = df.groupBy("name")
//      .agg(count(lit(1)).as("cnt"),sum(col("amount")).as("total_price"))
//
//    new_df.withColumn("cap",upperUDF(col("name"))).show()
//
//
//  }
//
//}
