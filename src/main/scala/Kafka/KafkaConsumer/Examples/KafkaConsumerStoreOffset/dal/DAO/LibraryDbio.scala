package Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DAO

import slick.jdbc.MySQLProfile.api._
import Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DTO._
import slick.jdbc.meta.MTable


class LibraryDbio {

  def insertOffset(offset: Offset): DBIO[Offset] = Query.writeOffsets += offset

  def createOffsetsTable() = Query.offsets.schema.create

  def isTableExist(table: String) = MTable.getTables(table)

  def getOffsetByTopicPartition(topic: String, partition: Int) = {
    Query.offsets.filter(t => t.topic === topic && t.partition === partition).map(_.offset).result
  }

  def updateOffsets(topic: String, partition: Int,newTopic :Long) = {
    val updateStmt = for {l <- Query.offsets if l.topic === topic && l.partition === partition } yield l.offset
    updateStmt.update(newTopic)
  }
}

object Query {
  val offsets = TableQuery[Offsets]

  val writeOffsets = offsets returning offsets
      .map(_.id) into ((Offset, id) => Offset.copy(id))


}
