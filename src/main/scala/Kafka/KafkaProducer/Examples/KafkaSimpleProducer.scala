package Kafka.KafkaProducer.Examples

import java.util.Properties

import Config.ConfigUtil._
import kafka.common.KafkaException
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.errors.{AuthorizationException, OutOfOrderSequenceException, ProducerFencedException}
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.LoggerFactory
import org.slf4j.Logger

//
//case class KafkaProducerConfigs(brokerList: String = conf.getString("App.kafka.brokers")) {
//  val properties = new Properties()
//  properties.put("bootstrap.servers", brokerList)
//  properties.put("key.serializer", classOf[StringSerializer])
//  properties.put("value.serializer", classOf[StringSerializer])
//  // properties.put("acks", "all")
//  //properties.put("retries","0")
//  properties.put("enable.idempotence", "true")
//  properties.put("transactional.id", "trn-2")
//
//
//}

class KafkaSimpleProducer {


  val producer = new KafkaProducer[String, String](KafkaProducerConfigs(conf.getString("App.kafka.brokers")))

  def produce(topic: String, messages: Iterable[String]): Unit = {

    //producer.initTransactions()
    try {

      //producer.beginTransaction()
      messages.foreach { m =>
        producer.send(new ProducerRecord[String, String](topic, m.hashCode().toString(), m))

      }
      //producer.commitTransaction()

    }
    catch {
      case e @ (_: ProducerFencedException | _:OutOfOrderSequenceException | _:AuthorizationException) => producer.close()
      case e: KafkaException => producer.abortTransaction()

      }

    producer.close()
    }

}

  object KafkaSimpleProducer {
    //@transient val logger: Logger = LoggerFactory.getLogger("org.apache.kafka.clients.producer.KafkaProducer")
    @transient val logger: Logger = LoggerFactory.getLogger(this.getClass)


    def main(args: Array[String]): Unit = {
      val topic = conf.getString("App.kafka.simple-producer.topic-name")
      val messages = Seq("Hello theres", "Mango hamuds","jopsa")

      val producerClass = new KafkaSimpleProducer()

      producerClass.produce(topic,messages)
    }

  }
