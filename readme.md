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
java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1 -tu ms
```
```
Benchmark                                      Mode  Cnt     Score     Error   Units
StringReplaceBenchmark.test4String            thrpt    6  1255.017 ± 230.012  ops/ms
StringReplaceBenchmark.test4StringUtils       thrpt    6  4068.229 ±  67.708  ops/ms
StringReplaceBenchmark.test4fast              thrpt    6  4821.035 ±  97.790  ops/ms
StringReplaceBenchmark.test4lang3StringUtils  thrpt    6  3186.007 ± 102.786  ops/ms
```


```
java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1  -bm thrpt,avgt -tu us
```
```
Benchmark                                      Mode  Cnt  Score   Error   Units
StringReplaceBenchmark.test4String            thrpt    6  1.238 ± 0.210  ops/us
StringReplaceBenchmark.test4StringUtils       thrpt    6  4.076 ± 0.049  ops/us
StringReplaceBenchmark.test4fast              thrpt    6  4.714 ± 0.044  ops/us
StringReplaceBenchmark.test4lang3StringUtils  thrpt    6  3.185 ± 0.042  ops/us
StringReplaceBenchmark.test4String             avgt    6  0.787 ± 0.174   us/op
StringReplaceBenchmark.test4StringUtils        avgt    6  0.250 ± 0.002   us/op
StringReplaceBenchmark.test4fast               avgt    6  0.211 ± 0.002   us/op
StringReplaceBenchmark.test4lang3StringUtils   avgt    6  0.317 ± 0.004   us/op
```

```
java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1 -prof gc
```