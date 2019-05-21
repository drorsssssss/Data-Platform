package Kafka.KafkaConsumer.Examples.KafkaConsumerStoreOffset.dal.Tables


import slick.jdbc.MySQLProfile.api._


class Offsets(tag: Tag) extends Table[(Int,String, Int, Long)](tag, "OFFSETS") {
  def id = column[Int]("ID",O.PrimaryKey, O.AutoInc)
  def topic = column[String]("TOPIC") // This is the primary key column
  def partition = column[Int]("PARTITION")
  def offset = column[Long]("OFFSET")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = (id,topic, partition, offset)

}
