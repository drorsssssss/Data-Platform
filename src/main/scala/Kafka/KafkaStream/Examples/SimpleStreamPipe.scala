package Kafka.KafkaStream.Examples

import java.util.Properties
import java.util.concurrent.TimeUnit
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}


  object SimpleStreamPipe extends App {
    import Serdes._

    val props: Properties = {
      val p = new Properties()
      p.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application")
      p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "ds-kafka-01.c.rtp-gcp-poc.internal:9092,ds-kafka-02.c.rtp-gcp-poc.internal:9092,ds-kafka-03.c.rtp-gcp-poc.internal:9092")
      p
    }

    val builder: StreamsBuilder = new StreamsBuilder
    val textLines: KStream[String, String] = builder.stream[String, String]("topic3")


    textLines.to("WordsWithCountsTopic")

    val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
    streams.start()

    sys.ShutdownHookThread {
      streams.close(10, TimeUnit.SECONDS)
    }
  }



