package com.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordReverse {

  def main(args: Array[String]) {
    if (args.length < 2) {
      println("Usage inputFile outputFile")
    }
    val inFile = args(0)
    val outFile = args(1)

    val conf = new SparkConf().setAppName("SPARK-WORDREVERSE")
    //Scala Spark Context.
    val sc = new SparkContext(conf)
    //Load input data
    val input = sc.textFile(inFile)
    //Split into words using regex
    val words = input.flatMap(line => line.split("\\s+"))
    //Reverse the word
    val reverse = words.map(word => word.reverse)
    // Save output to a file.
    reverse.saveAsTextFile(outFile)
    sc.stop()
  }
}
