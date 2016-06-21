name := "spark-scala-examples"

version := "1.0"

libraryDependencies ++= Seq("org.apache.spark" % "spark-core_2.10" % "1.6.1" % "provided",
                             "org.apache.spark" % "spark-streaming_2.10" % "1.6.1" % "provided",
                             "org.apache.spark" % "spark-mllib_2.10" % "1.3.0" % "provided",
                             "org.apache.spark" % "spark-sql_2.10" % "1.6.1" % "provided")