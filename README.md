# Spark hands on introduction
This repository contains slides and code used for hands on spark introduction workshop conducted by me.

## Setup local environment

### Install basics
- jdk 1.8
- sbt 1.2.1 https://www.scala-sbt.org/download.html

### Install Coursier
https://get-coursier.io/docs/cli-overview.html#installation

### Install Ammonite and Spark
Create spark context with following instructions (using coursier) https://github.com/alexarchambault/ammonite-spark#quick-start follow instructions till line:
```scala
val n = rdd.map(_ + 1).sum()):
```
https://github.com/alexarchambault/ammonite-spark#quick-start

### Install spark (only if your IDE is not able to run including provided libraries)
Download a pre-built version of Apache Spark 2.3 (do not use Spark 2.4; it has a bug and only works with Linux) from https://spark.apache.org/downloads.html
set SPARK_HOME and add it to PATH

### Setting up twitter account
You'll need to set up a developer account with Twitter, so you can practice streaming in real-time data from Twitter using Spark Streaming.

You should probably take care of this now so it'll be ready for you when you get to that part of the course.

Just head to developer.twitter.com to start the process. When they ask about your use case, check off the "student project / learning to code" option, and tell them you're going to practice organizing streamed Tweets by hashtag as a class exercise in the course "Apache Spark 2 with Scala" on Udemy. Check "no" when asked if you'll be providing this data to a government entity.

Some other answers you might need to provide:

I am using the Twitter API to learn the Spark Streaming  Twitter API 

I plan to analyze Tweets, to learn streaming and aggregation

I don't plan to tweet using API, I plan to read from Twitter stream

I don't plan to display Tweets, I will only consume them

I need the account for learning purposes and proof of concepts

As long as you clearly answer the questions in their application and make it clear that you need this account for educational purposes, you shouldn't have a problem.

When your account is ready please create app in order to create API key https://developer.twitter.com/en/apps/ 

## Agenda
1. Slides and theory
2. Basic spark app
3. Advanced spark app
4. SparkSQL, DataFrames, DataSets
5. Spark Streaming
6. GraphX introduction