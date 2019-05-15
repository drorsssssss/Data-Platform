package Kafka.KafkaProducer.Simulator

import Config.ConfigUtil._
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import scala.collection.JavaConversions._


class EventsGeneration(topic:String,brokers:String, eventsList: List[String]) {

  private val producer = new KafkaProducer[String, String](KafkaProducerConfigs(brokers))

  def Generate(numEvents:Int) = {
    val r = scala.util.Random

    for (i <- 1 to numEvents) {
      val randomInt = r.nextInt(eventsList.length)
      producer.send(new ProducerRecord[String, String](topic, eventsList(randomInt)))}

    producer.close()
  }


  }

object EventsGeneration{

  def main(args: Array[String]): Unit = {
    val topic = conf.getString("App.kafka.simulator.topic-name")
    val brokers = conf.getString("App.kafka.brokers")
    val events = conf.getStringList("App.kafka.simulator.events").toList

    val eventsGen = new EventsGeneration(topic,brokers,events)

    eventsGen.Generate(10)

  }
}
