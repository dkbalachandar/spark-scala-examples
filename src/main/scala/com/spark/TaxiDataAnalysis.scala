package com.spark

import org.apache.spark.{SparkConf, SparkContext}

object TaxiDataAnalysis {
  def main(args: Array[String]) {

    if (args.length < 1) {
      println("Usage inputFile")
      return
    }
    val conf = new SparkConf().setAppName("TAXI-DATA-ANALYSIS")
    //Scala Spark Context.
    val sc = new SparkContext(conf)
    //Load data.
    val taxiData = sc.textFile(args(0))

    //Split the row content with ,
    val taxiDataParse = taxiData.map(line => line.split(","));
    //Map the values based on license id of the driver.
    val taxiLicenseKey = taxiDataParse.map(values =>(values(1), 1))
    //Reduce the data by license id
    val taxiLicenseCounts = taxiLicenseKey.reduceByKey((v1,v2)=>v1+v2)
    //Reduce by key
    for (pair <-taxiLicenseCounts.map(_.swap).top(5)) println("Driver License ID %s had %s Trips".format(pair._2, pair._1))
    sc.stop()
  }
}
