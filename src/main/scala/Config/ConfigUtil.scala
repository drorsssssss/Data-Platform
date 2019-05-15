package Config

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.spark.sql.SparkSession

object ConfigUtil {

  def conf():Config = ConfigFactory.load()

  def spark(appName: String, master: String): SparkSession = SparkSession
      .builder
      .appName(appName)
      .master(master)
      .getOrCreate()


  def KafkaProducerConfigs(brokerList: String): Properties = {
    val properties = new Properties()
    properties.put("bootstrap.servers", brokerList)
    properties.put("key.serializer", classOf[StringSerializer])
    properties.put("value.serializer", classOf[StringSerializer])
    properties.put("acks", "all")
    properties.put("retries","3")
    //properties.put("enable.idempotence", "true")
    //properties.put("transactional.id", "trn-2")
    properties


  }









}
