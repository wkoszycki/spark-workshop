name := "spark-workshop"

version := "0.1"

scalaVersion := "2.11.12"
val sparkVersion = "2.2.0"
val twitterUtilsVersion = "4.0.4"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-graphx" % sparkVersion % "provided",
  "org.apache.bahir" %% "spark-streaming-twitter" % sparkVersion,
  "org.twitter4j" % "twitter4j-core" % twitterUtilsVersion,
  "org.twitter4j" % "twitter4j-stream" % twitterUtilsVersion,
  "log4j" % "log4j" % "1.2.17"
)