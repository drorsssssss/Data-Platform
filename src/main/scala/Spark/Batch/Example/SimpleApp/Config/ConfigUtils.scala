package Spark.Batch.Example.SimpleApp.Config

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import com.typesafe.config.{Config, ConfigFactory}
import java.io._
import scala.collection.JavaConversions._

object ConfigUtils {

  val sparkSession  = SparkSession.builder().config(buildSparkConfig).getOrCreate()

  private def buildSparkConfig(): SparkConf={
    val conf:SparkConf = new SparkConf()

    val hoc = getHoconEntryPoint()
    val hoc_set = hoc.getConfig("App.SparkConf").entrySet()
    hoc_set.foreach(x=>conf.set(x.getKey,x.getValue.unwrapped().toString))

    conf

  }

  def getHoconEntryPoint():Config = ConfigFactory.parseFile(new File("/Users/dsivan/GitRepos/Data-Platform/src/main/scala/Spark/Batch/Example/SimpleApp/Config/hocon/application.conf"))

}
