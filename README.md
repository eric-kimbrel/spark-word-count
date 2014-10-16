spark-word-count
================

Simple spark template project to use as a getting started point when writing new [Apache Spark](https://spark.apache.org/) projects.



To run locally use an IDE such as IntelJ IDEA or Eclipse to run the main method.  Make sure to set paramaters for an input and output file.

To run on a cluster
```
# on your machine
gradle clean dist
scp -r build/dist <location on your cluster you can launch jobs from>

# on the cluster
cd <location on your cluster you can launch jobs from>/dist
./yarn-runner hdfs://<name node>/<path to input> hdfs://<nade node>/<path to output>
```

