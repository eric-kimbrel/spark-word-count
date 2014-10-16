package com.mydomain.wordcount

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.SparkContext._


object Main {

  val appName = "spark-word-count"

  def main(args: Array[String]) {


    // get input and output path from command line arguments
    val inputPath = args(0)
    val outputPath = args(1)

    println(s"Reading words from $inputPath")
    println(s"Writing word counts to $outputPath")

    // Create a spark context
    val conf = new SparkConf().setAppName(appName)
    //conf.setMaster("local")  // for local test only
    val sc = new SparkContext(conf)

    // read the input into an RDD
    val inputRdd = sc.textFile(inputPath)

    // remove punctuation and split each sentence into individual words
    val words = inputRdd.flatMap(sentence => {
      sentence
        .replace(",","")
        .replace(".","")
        .replace("!","")
        .replace("?","")
        .replace("'","")
        .replace("`","")
        .replace("\"","")
        .replace("\t"," ")
        .replace("\n"," ")   /* new lines are unlikely since they would split the input to another row in the RDD */
        .split(" ")
    })

    val counts = words.map(word => (word,1)).reduceByKey( (x,y) => x+y )

    counts.saveAsTextFile(outputPath)

  }


}


