package com.github.koszycki.spark.workshop

import org.apache.log4j._
import org.apache.spark.sql.{Encoder, Encoders, _}

object DataFrames {

  case class Person(ID: Int, name: String, age: Int, numFriends: Int)


  def mapper(line: String): Person = {
    val fields = line.split(',')

    val person: Person = Person(fields(0).toInt, fields(1), fields(2).toInt, fields(3).toInt)
    return person
  }

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    //    Ammonite stuff to copy paste
    //    import $ivy.`org.apache.spark::spark-sql:2.4.0`
    //    import $ivy.`sh.almond::ammonite-spark:0.3.0`
    //    import org.apache.spark.sql._
    //
    //    val spark = {
    //      AmmoniteSparkSession.builder()
    //        .master("local[*]")
    //        .getOrCreate()
    //    }


    // Use new SparkSession interface in Spark 2.0
    val spark = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "file:///C:/temp") // Necessary to work around a Windows bug in Spark 2.0.0; omit if you're not on Windows.
      .getOrCreate()

    // Convert our csv file to a DataSet, using our Person case
    // class to infer the schema.
    import spark.implicits._

    val df = spark.read.format("csv").option("delimiter", ",").option("header", "true").option("inferSchema", "true").load("/home/developer/code/priv/spark-workshop/data/fakefriends.csv")


    val peopleDS = spark.read.json("data/people.json").as[Person]

    // There are lots of other ways to make a DataFrame.
    // For example, spark.read.json("json file path")
    // or sqlContext.table("Hive table name")

    println("Here is our inferred schema:")
    peopleDS.printSchema()

    println("Let's select the name column:")
    peopleDS.select("name").show()

    println("Filter out anyone over 21:")
    peopleDS.filter(peopleDS("age") < 21).show()

    println("Group by age:")
    peopleDS.groupBy("age").count().show()

    println("Make everyone 10 years older:")
    peopleDS.select(peopleDS("name"), peopleDS("age") + 10).show()

    spark.stop()
  }
}