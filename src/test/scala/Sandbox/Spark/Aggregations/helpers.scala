package Sandbox.Spark.Aggregations

import org.apache.spark.sql.functions.udf

class helpers {

  val upper: String=>String = _.toUpperCase
  val upperUDF = udf[String,String](upper)

}
