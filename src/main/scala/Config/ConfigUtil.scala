package Config

import java.util.Properties

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}
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

  def KafkaConsumerConfigs(brokerList: String): Properties = {
    val properties = new Properties()
    properties.put("bootstrap.servers", brokerList)
    properties.put("key.deserializer", classOf[StringDeserializer])
    properties.put("value.deserializer", classOf[StringDeserializer])
    properties.put("group.id", "q1-group-1")
    properties.put("enable.auto.commit", "false")
    //properties.put("auto.commit.interval.ms", "1000")
    properties.put("auto.offset.reset", "latest")

    properties


  }








}
