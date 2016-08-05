package com.spark

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object USANameAnalysis {
  def main(args: Array[String]) {

    if (args.length < 3) {

      /*
      Run as below
      bin/spark-submit --class com.spark.USANameAnalysis --master local spark-scala-examples-assembly-1.0.jar
      file:///usr/local/spark/NationalNames.csv file:///usr/local/spark/output Zura

      */
      println("Usage inputFile nameToQuery")
      return
    }

    val conf = new SparkConf().setAppName("NAME-ANALYSIS")

    //Scala Spark Context.
    val sc = new SparkContext(conf)

    //Create the SQL context
    val sqlContext = new SQLContext(sc)

    //Load the CSV data
    val df = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(args(0))

    df.printSchema()

    df.columns.foreach(println)

    //Then filter with name and output the data to an another CSV file
    val selectedData = df.filter(df("Name") === args(2))

    selectedData.collect().foreach(println)

    selectedData.write.format("com.databricks.spark.csv")
       .option("header", "true")
       .save(args(1))
    /*
     Output file content is given below

     Id,Name,Year,Gender,Count
     32455,Zura,1893,F,6
     35552,Zura,1894,F,5
     108497,Zura,1913,F,5
     143367,Zura,1917,F,6

     */

     /*
      We can also map the DF to a table and query against it.

      df.registerTempTable("USA_NAME_DATA")
      val query = "SELECT * FROM USA_NAME_DATA where Name IN ('" + args(1) + "')"
      val specificResults = sqlContext.sql(query).collect()
      specificResults.foreach(println)

     */
    sc.stop()
  }
}
