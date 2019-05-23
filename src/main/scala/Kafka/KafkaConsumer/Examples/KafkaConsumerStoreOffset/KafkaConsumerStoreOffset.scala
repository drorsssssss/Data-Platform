package Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset


import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import Config.ConfigUtil._

import scala.collection.JavaConversions._
import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DTO._
import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DAO._
import org.apache.kafka.common.TopicPartition
import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.AccessTables._

class KafkaConsumerStoreOffset {

  private val consumer = new KafkaConsumer[String, String](KafkaConsumerConfigs(conf.getString("App.kafka.brokers")))
  private val accessTablesFuncs = new accessTables(new LibraryDbio)

  def consume(topics:List[String])= {

    try {
      consumer.assign(Seq(new TopicPartition(topics(0),0),new TopicPartition(topics(0),1),new TopicPartition(topics(0),2)))
      accessTablesFuncs.isTableExists("OFFSETS") match  {
        case false=> {
          accessTablesFuncs.createOffsetsTable()
          accessTablesFuncs.insertOffset(new Offset(1,topics(0),0,0))
          accessTablesFuncs.insertOffset(new Offset(1,topics(0),1,0))
          accessTablesFuncs.insertOffset(new Offset(1,topics(0),2,0))
        }
        case true=> print("OFFSETS Table already created\n")

      }


      while (true) {

        consumer.seek(new TopicPartition(topics(0),0),accessTablesFuncs.getOffsetByTopicPartition(topics(0),0)+1)
        consumer.seek(new TopicPartition(topics(0),1),accessTablesFuncs.getOffsetByTopicPartition(topics(0),1)+1)
        consumer.seek(new TopicPartition(topics(0),2),accessTablesFuncs.getOffsetByTopicPartition(topics(0),2)+1)

        val records: ConsumerRecords[String, String] = consumer.poll(100)

        for (record <- records if !records.isEmpty) {
          print(s"{ThreadID =${Thread.currentThread().getId}, partition =${record.partition()} ,offset = ${record.offset()}\n")

          //Update offsets in db
          accessTablesFuncs.updateOffsets(record.topic(),record.partition(),record.offset())
                  }

        consumer.commitSync()

      }
    }

    catch{
      case e : Throwable =>{print(e.printStackTrace());consumer.close()}
      case _ => {print(" closingconsumer"); consumer.close()}
    }
    finally accessTablesFuncs.closeDB()


  }

}

object KafkaConsumerStoreOffset{

  def main(args: Array[String]): Unit = {
    val topics = conf.getStringList("App.kafka.simple-consumer-store-offset.topic-name").toList

    val consumerClass = new KafkaConsumerStoreOffset()
    consumerClass.consume(topics)

  }
}


