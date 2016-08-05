package com.spark

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object PopulationDataAnalysis {
  def main(args: Array[String]) {
    if (args.length < 1) {
      println("Usage inputFile")
      return
    }
    val inputFile = args(0)
    val conf = new SparkConf().setAppName("SPARK-SQL")
    val sc = new SparkContext(conf)
    //Create the SQL context
    val sqlContext = new SQLContext(sc)
    //Read the data from the JSON file
    val population = sqlContext.read.json(inputFile)
    //Map it to a table
    population.registerTempTable("population")
    //Query it
    val allResults = sqlContext.sql("SELECT * FROM population").collect()
    print("Print all records::")
    allResults.foreach(println)
    val queryResults = sqlContext.sql("SELECT age, females, males, total, year FROM population where age IN (1,2,3)").collect()
    print("Query and Print records::")
    queryResults.foreach(println)
    sc.stop()
  }
}