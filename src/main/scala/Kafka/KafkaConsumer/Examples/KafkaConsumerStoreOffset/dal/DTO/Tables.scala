package Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.DAL.DTO


import slick.jdbc.MySQLProfile.api._

case class Offset(id: Int,topic: String, partition: Int, offset: Long)

class Offsets(tag: Tag) extends Table[Offset](tag, "OFFSETS") {
  def id = column[Int]("ID",O.PrimaryKey, O.AutoInc)
  def topic = column[String]("TOPIC") // This is the primary key column
  def partition = column[Int]("PARTITION")
  def offset = column[Long]("OFFSET")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id,topic, partition, offset) <> (Offset.tupled, Offset.unapply)

}
