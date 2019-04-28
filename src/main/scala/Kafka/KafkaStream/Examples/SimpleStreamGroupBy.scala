package Kafka.KafkaStream.Examples

import java.util.Properties
import java.util.concurrent.TimeUnit

import Config.ConfigUtil._
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}


object SimpleStreamGroupBy extends App {
  import Serdes._

  val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, conf.getString("App.kafka.streams.simple_groupby_stream.application_name"))
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, conf.getString("App.kafka.brokers"))
    p
  }

  val builder: StreamsBuilder = new StreamsBuilder
  val textLines: KStream[String, String] = builder.stream[String, String](conf.getString("App.kafka.streams.simple_groupby_stream.input_topic"))


  val wordCounts: KTable[String, Long] = textLines
    .flatMapValues(textLine => textLine.toLowerCase.split("\\W+"))
    .groupBy((_, word) => word)
    .count() //TODO Need to understand if the counted value should be materialized


  wordCounts.toStream.to(conf.getString("App.kafka.streams.simple_groupby_stream.output_topic"))
  //TODO Count value didn't persisted in the kafka message. Need to understand why

  val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
  streams.start()

  sys.ShutdownHookThread {
    streams.close(10, TimeUnit.SECONDS)
  }
}



