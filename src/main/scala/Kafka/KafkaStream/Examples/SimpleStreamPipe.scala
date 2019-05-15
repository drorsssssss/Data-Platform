package Kafka.KafkaStream.Examples

import java.util.Properties
import java.util.concurrent.TimeUnit
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.scala.kstream._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import Config.ConfigUtil._



  object SimpleStreamPipe extends App {
    import Serdes._

    val props: Properties = {
      val p = new Properties()
      p.put(StreamsConfig.APPLICATION_ID_CONFIG, conf.getString("App.kafka.streams.simple-pipe-stream.application-name"))
      p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, conf.getString("App.kafka.brokers"))
      p
    }

    val builder: StreamsBuilder = new StreamsBuilder
    val textLines: KStream[String, String] = builder.stream[String, String](conf.getString("App.kafka.streams.simple-pipe-stream.input-topic"))


    textLines.to(conf.getString("App.kafka.streams.simple-pipe-stream.output-topic"))

    val streams: KafkaStreams = new KafkaStreams(builder.build(), props)
    streams.start()

    sys.ShutdownHookThread {
      streams.close(10, TimeUnit.SECONDS)
    }
  }



