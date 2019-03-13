package com.github.koszycki.spark.workshop

import java.nio.charset.CodingErrorAction

import org.apache.log4j._
import org.apache.spark._

import scala.io.Codec

/** Find the movies with the most ratings (data goes to 1998).
  *
  * Data structure looks like this:
  * UserId, MovieId, Rating Timestamp
  * 196	242	3	881250949
  * 186	302	3	891717742
  * 22	377	1	878887116
  *
  * */
object PopularMovies {

  /** Our main function where the action happens */
  def main(args: Array[String]) {

    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // Create a SparkContext using every core of the local machine
    val sc = new SparkContext("local[*]", "PopularMovies")

    // Read in each rating line
    val lines = sc.textFile("data/ml-100k/u.data")

    // Map to (movieID, 1) tuples (split on tab)
    val movies = lines.map(x => (x.split("\t")(1).toInt, 1))

    // Count up all the 1's for each movie
    val movieCounts = movies.reduceByKey((x, y) => x + y)

    // Flip (movieID, count) to (count, movieID)
    val flipped = movieCounts.map(x => (x._2, x._1))

    // Sort
    val sortedMovies = flipped.sortByKey()

    // Collect and print results
    val results = sortedMovies.collect()

    // will print most popular movie based on how many times it was rated
    results.foreach(println)

    // TODO: 1. What the heck is movie id x ? we could put movies definition into
    // driver memory and transform it on the end but imagine we need it before collecting
    // results it's huge so try to use broadcast variables instead.
    // Broadcast variables allow the programmer to keep a read-only variable cached on each machine,
    // rather than shipping a copy of it with tasks.
    //
    // https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-broadcast.html
    // https://spark.apache.org/docs/2.2.0/rdd-programming-guide.html#broadcast-variables
    //
    // Baking formula:
    // 1. Implement loadMovieNames as pure file reading operation
    // 2. create broadcast variable
    // 3. map names to id's before collecting result to driver
  }

  // Handle character encoding issues:
  implicit val codec: Codec = Codec("UTF-8")
  codec.onMalformedInput(CodingErrorAction.REPLACE)
  codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

  /**
    * Movie names can be found in data/ml-100k/u.item file
    *
    * @return movieId -> movieName map
    */
  def loadMovieNames(): Map[Int, String] = ???
}

