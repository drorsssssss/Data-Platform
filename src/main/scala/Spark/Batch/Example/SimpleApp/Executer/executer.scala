package Spark.Batch.Example.SimpleApp.Executer

import Spark.Batch.Example.SimpleApp.Config.ConfigUtils._
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions._



object executer {


  def main(args: Array[String]): Unit = {
    val spark = sparkSession
    import spark.implicits._

    val data_path = "/Users/dsivan/GitRepos/Data-Platform/src/test/resources/SampleData/FL_insurance_sample.csv"

    var df = spark.read.option("header",true).csv(data_path).withColumn("nc",lit(0))
    Range(1,9).foreach(i=>{
      var df_new = spark.read.option("header",true).csv(data_path).withColumn("nc",lit(i))
      df=df.union(df_new)

    })


    val res = df.groupBy($"nc").count()
    res.show()
    println(df.count())



  }

}
