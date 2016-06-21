package com.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}


object WordCountOnStreamingData {

  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("WordCountOnStreamingData")
    // Create a StreamingContext with a 1 second batch size
    val ssc = new StreamingContext(conf, Seconds(1))
    // Create a DStream from all the input on port 7777
    val lines = ssc.socketTextStream("localhost", 7777)
    // Split each line into words
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))

    val wordCounts = pairs.reduceByKey(_ + _)
    // Print the first ten elements of each RDD generated in this DStream to the console
    wordCounts.print()

    ssc.start()
    // Wait for 10 sec then exit. To run forever call without a timeout
    ssc.awaitTermination(10000)
    ssc.stop()
  }
}
