package com.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]) {
    if (args.length < 2) {
      println("Usage inputFile outputFile")
    }
    val inFile = args(0)
    val outFile = args(1)

    val conf = new SparkConf().setAppName("SPARK-WORDCOUNT")
    //Scala Spark Context.
    val sc = new SparkContext(conf)
    //Load input data
    val input = sc.textFile(inFile)
    //Split into words using regex
    val words = input.flatMap(line => line.split("\\s+"))
    //Reduce by key
    val counts = words.map(word => (word, 1)).reduceByKey { case (a, b) => a + b }
    // Save output to a file.
    counts.saveAsTextFile(outFile)
    sc.stop()
  }
}
