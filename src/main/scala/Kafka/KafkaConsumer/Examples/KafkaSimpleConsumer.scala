package Kafka.KafkaConsumer.Examples


import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import Config.ConfigUtil._

import scala.collection.JavaConversions._

class KafkaSimpleConsumer {

  private val consumer = new KafkaConsumer[String, String](KafkaConsumerConfigs(conf.getString("App.kafka.brokers")))

  def consume(topics:List[String])= {

    try {
      consumer.subscribe(topics)
      while (true) {
        val records: ConsumerRecords[String, String] = consumer.poll(100)
        for (record <- records) {
          print(s"{ThreadID =${Thread.currentThread().getId}, partition =${record.partition()} ,offset = ${record.offset()}, key = ${record.key()}, value = ${record.value()}\n")

        }
        consumer.commitSync()

      }
    }
    catch{
      case _ => {print("closing consumer"); consumer.close()}
    }


  }

}

object KafkaSimpleConsumer{

  def main(args: Array[String]): Unit = {
    val topics = conf.getStringList("App.kafka.simple-consumer.topic-name").toList

    val consumerClass = new KafkaSimpleConsumer()
    consumerClass.consume(topics)

  }
}


