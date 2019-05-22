package Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.AccessTables

import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DAO.LibraryDbio
import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DTO.Offset
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await}
import scala.concurrent.ExecutionContext.Implicits.global

class accessTables(libraryDbio: LibraryDbio) {

  private val db = Database.forConfig("App.kafka.simple-consumer-store-offset.db")

  def insertOffset(offset: Offset) = {
    val action = for {offset <- libraryDbio.insertOffset(offset)} yield offset
    Await.result(db.run(action),Duration.Inf)
  }

  def createOffsetsTable() = Await.result(db.run(libraryDbio.createOffsetsTable()),Duration.Inf)

  def isTableExists(table: String) = Await.result(db.run(libraryDbio.isTableExist(table)),Duration.Inf).size  match {
    case x if x>0 => true
    case _ => false
  }

  def getOffsetByTopicPartition(topic: String,partition: Int) = Await.result(db.run(libraryDbio.getOffsetByTopicPartition(topic,partition)),Duration.Inf).head

  def updateOffsets(topic: String,partition: Int, newOffset: Long) = Await.result(db.run(libraryDbio.updateOffsets(topic,partition,newOffset)),Duration.Inf)

}
