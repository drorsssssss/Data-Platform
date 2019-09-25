name := "Data-Platform"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "com.typesafe" % "config" % "1.3.4"

libraryDependencies += "org.apache.kafka" %% "kafka-streams-scala" % "2.1.1"

libraryDependencies += "org.apache.kafka" %% "kafka" % "2.1.1"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.5"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.5"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1"

//libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.4.3"

//libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.3"

dependencyOverrides += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.9.8"

//libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "com.typesafe.slick" %% "slick" % "3.3.0"

libraryDependencies += "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0"

libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.6"








