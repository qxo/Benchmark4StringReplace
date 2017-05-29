JMH benchmark for String replace
================================================
ref: 
* http://openjdk.java.net/projects/code-tools/jmh
* http://java-performance.info/jmh/
* http://java-performance.info/introduction-jmh-profilers/
* http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/

#build jar
```
mvn clean package
```

#get help from cmd `java -jar target/benchmarks.jar  -h`
* List matching benchmarks -- `java -jar target/benchmarks.jar -l `
* List profilers -- `java -jar target/benchmarks.jar  -lprof `

#run
```
java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1 -prof gc

java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1  -bm thrpt,avgt -tu us

java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1 -tu ms
```
