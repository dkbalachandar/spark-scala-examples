Spark Word count and word reverse jobs in Scala

1. Clone this repository
2. Package it with sbt assembly
3. Copy the spark-scala-examples-assembly-1.0.jar to spark installed location[Assume that its /usr/local/spark]
4. Copy the input.txt[src/main/resources] to /usr/local/spark/spp folder
5. Run with the below command

  a) spark-submit --class com.spark.WordCount --master local spark-scala-examples-assembly-1.0.jar file:///usr/local/spark/spp/input.txt file:///usr/local/spark/spp/output1

  b) spark-submit --class com.spark.WordReverse --master local spark-scala-examples-assembly-1.0.jar file:///usr/local/spark/spp/input.txt file:///usr/local/spark/spp/output2
 
 6. Check the /usr/local/spark/spp folder and make sure that the output files are created


To run the spark job against Hadoop, then follow the below steps

1. Clone this repository
2. Package it with sbt assembly
3. Copy the spark-scala-examples-assembly-1.0.jar to spark installed location[Assume that its /usr/local/spark]
4. Copy the input.txt[src/main/resources] to hadoop by running the below command. [ bin/hadoop fs -copyFromLocal <<LOCAL_FILE_WITH_DIR>> /spark/input 
5. Set the HADOOP_CONF_DIR env. [export HADOOP_CONF_DIR=/usr/local/hadoop/etc/hadoop]
5. Run with the below command

  a) spark-submit --class com.spark.WordCount --master yarn spark-scala-examples-assembly-1.0.jar /spark/input /spark/output

  b) spark-submit --class com.spark.WordReverse --master local spark-scala-examples-assembly-1.0.jar /spark/input /spark/output
 
 6. Check the /spark/output folder and make sure that the output files are created

=================================================================================================================================================================================

Follow the above steps for running other spark Jobs
 
