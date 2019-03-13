package com.github.koszycki.spark.workshop

import org.apache.log4j._
import org.apache.spark._

/** Count up how many of each word appears in a book as simply as possible. */
object WordCount {

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    val filteredWords = Set("an","a","the","to","i")
    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "WordCount")
    // load file
    val textFile = sc.textFile("data/pride_and_preduce.txt")
    // split words
    val wordsSplitted = textFile.flatMap(line => line.split("\\W+"))
    // count of occurrences
    val counts = wordsSplitted.filter(s=> !filteredWords.contains(s)).map(word => (word.toLowerCase, 1))
      .reduceByKey(_ + _)

    val result = counts.collect().sortBy(t => t._2)(Ordering[Int].reverse)

    result.foreach { tuple =>
      println(s"${tuple._1} - ${tuple._2} ")
    }
    // TODO: 1. get rid of question marks dots commas so we get pure words
    // TODO: 2. make words lower/upper equals
    // TODO: 3. filter out connectives (the, a, an etc.)
  }

}
