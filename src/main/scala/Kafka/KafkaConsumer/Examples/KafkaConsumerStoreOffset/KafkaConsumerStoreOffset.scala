package Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset


import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import Config.ConfigUtil._

import scala.collection.JavaConversions._
import slick.jdbc.MySQLProfile.api._
import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DTO._
import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DAO._
import org.apache.kafka.common.TopicPartition

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.AccessTables._

class KafkaConsumerStoreOffset {

  private val offsets = TableQuery[Offsets]
  private val db = Database.forConfig("App.kafka.simple-consumer-store-offset.db")
  private val consumer = new KafkaConsumer[String, String](KafkaConsumerConfigs(conf.getString("App.kafka.brokers")))
  private val accessTablesFuncs = new accessTables(new LibraryDbio)

  def consume(topics:List[String])= {

    try {
      consumer.subscribe(topics)
      accessTablesFuncs.isTableExists("OFFSETS") match  {
        case false=> {
          accessTablesFuncs.createOffsetsTable()
          accessTablesFuncs.insertOffset(new Offset(1,topics(0),0,0))
          accessTablesFuncs.insertOffset(new Offset(1,topics(0),1,0))
          accessTablesFuncs.insertOffset(new Offset(1,topics(0),2,0))
        }
        case true=> print("OFFSETS Table already created")

      }


      //Create offset table if not exist with default values
//      val FutureSetupTable = db.run(DBIO.seq((offsets.schema).createIfNotExists,
//        offsets += (1,topics(0),0,0),
//        offsets += (1,topics(0),1,0),
//        offsets += (1,topics(0),2,0)
//      ))
//      Await.result(FutureSetupTable, Duration.Inf)


      while (true) {
        //TODO Get offsets from db
        consumer.seek(new TopicPartition(topics(0),0),accessTablesFuncs.getOffsetByTopicPartition(topics(0),0))
        consumer.seek(new TopicPartition(topics(0),1),accessTablesFuncs.getOffsetByTopicPartition(topics(0),1))
        consumer.seek(new TopicPartition(topics(0),2),accessTablesFuncs.getOffsetByTopicPartition(topics(0),2))

        val records: ConsumerRecords[String, String] = consumer.poll(100)

        for (record <- records if !records.isEmpty) {
          print(s"{ThreadID =${Thread.currentThread().getId}, partition =${record.partition()} ,offset = ${record.offset()}\n")

          //Update offsets in db
          val updateOffsets = for {l <- offsets if l.topic === record.topic() && l.partition === record.partition() } yield l.offset
          val f = db.run(updateOffsets.update(record.offset()))
          Await.result(f, Duration.Inf)
        }

        consumer.commitSync()

      }
    }

    catch{
      case e : Throwable =>{print(e.printStackTrace());consumer.close()}
      case _ => {print(" closingconsumer"); consumer.close()}
    }
    finally db.close()


  }

}

object KafkaConsumerStoreOffset{

  def main(args: Array[String]): Unit = {
    val topics = conf.getStringList("App.kafka.simple-consumer-store-offset.topic-name").toList

    val consumerClass = new KafkaConsumerStoreOffset()
    consumerClass.consume(topics)

  }
}


