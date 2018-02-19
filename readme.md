# JMH benchmark for String replace

This project benchmark String replacement performance with separate common implementations

* String - JDK String replacement
* StringUtils - Commons Lang StringUtils
* Lang3StringUtils - Commons Lang 3 StringUtils
* OSGL - [OSGL Java tool (2.0.0-SNAPSHOT)](https://github.com/osglworks/java-tool/tree/develop)
* Fast - a [built in](https://github.com/greenlaw110/Benchmark4StringReplace/blob/master/src/main/java/sample/StringReplaceBenchmark.java#L159) fast string replace function

The project measures String replacement in several use cases:

* Short text - replace `AA` in `AAAAAAAAAABBB` to `B`
* Short text no match - replace `XYZ` in `AAAAAAAAAABBB` to `B`
* Long text - replace `occurrence` in [this file content](https://github.com/greenlaw110/Benchmark4StringReplace/blob/master/src/main/resources/long_str.txt) to `appearance`
* Long text no match - replace `aaaxyz0001` in [this file content](https://github.com/greenlaw110/Benchmark4StringReplace/blob/master/src/main/resources/long_str.txt) to `appearance`

Benchmark result

| measure item | String | StringUtils | Lang3StringUtils | OSGL  | Fast  |
| :---         |   ---: |        ---: |             ---: |  ---: |  ---: |
| short text | 2647.313/ms | 8399.156/ms | 7092.984/ms | 8584.325/ms | **9558.353/ms** |
| long text | 73.793/ms | 155.973/ms | 152.580/ms | **223.589/ms** | 149.660/ms |
| short text no match | 7113.214/ms | 102227.568/ms | 87770.331/ms | **103388.380/ms** | 99851.409/ms |
| long text no match | 260.422/ms | 358.311/ms | 353.155/ms | **505.472/ms** | 328.562/ms |

Benchmark GC count

| measure item | String | StringUtils | Lang3StringUtils | OSGL  | Fast  |
| :---         |   ---: |        ---: |             ---: |  ---: |  ---: |
| short text | 15 | 13 | 10 | 10 | 19 |
| long text | 41 | 46 | 48 | **21** | 32 |
| short text no match | 9 | 0 | 0 | 0 | 0 |
| long text no match | 33 | 0 | 0 | 0 | 0 |

## Run the project

build jar

```
mvn clean package
```

get help from cmd `java -jar target/benchmarks.jar  -h`

* List matching benchmarks -- `java -jar target/benchmarks.jar -l `
* List profilers -- `java -jar target/benchmarks.jar  -lprof `

## Local environment test results

My local environment is a toshiba laptop with i7-4700MQ CPU, 16GB RAM and 250GB SSD

Command

```
java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1 -tu ms
```

Result

```
Benchmark                                                Mode  Cnt       Score      Error   Units
StringReplaceBenchmark.testFast                         thrpt    6    9558.353 ±  154.062  ops/ms
StringReplaceBenchmark.testFastLong                     thrpt    6     149.660 ±    3.147  ops/ms
StringReplaceBenchmark.testFastLongNoMatch              thrpt    6     328.562 ±    4.251  ops/ms
StringReplaceBenchmark.testFastNoMatch                  thrpt    6   99851.409 ± 5857.649  ops/ms
StringReplaceBenchmark.testLang3StringUtils             thrpt    6    7092.984 ±  185.122  ops/ms
StringReplaceBenchmark.testLang3StringUtilsLong         thrpt    6     152.580 ±    5.777  ops/ms
StringReplaceBenchmark.testLang3StringUtilsLongNoMatch  thrpt    6     353.155 ±    4.134  ops/ms
StringReplaceBenchmark.testLang3StringUtilsNoMatch      thrpt    6   87770.331 ± 1182.989  ops/ms
StringReplaceBenchmark.testOsgl                         thrpt    6    8584.325 ±   72.625  ops/ms
StringReplaceBenchmark.testOsglLong                     thrpt    6     223.589 ±    1.474  ops/ms
StringReplaceBenchmark.testOsglLongNoMatch              thrpt    6     505.472 ±   13.480  ops/ms
StringReplaceBenchmark.testOsglNoMatch                  thrpt    6  103388.380 ± 1142.023  ops/ms
StringReplaceBenchmark.testString                       thrpt    6    2647.313 ±  251.928  ops/ms
StringReplaceBenchmark.testStringLong                   thrpt    6      73.793 ±    2.871  ops/ms
StringReplaceBenchmark.testStringLongNoMatch            thrpt    6     260.422 ±    3.556  ops/ms
StringReplaceBenchmark.testStringNoMatch                thrpt    6    7113.214 ±  120.101  ops/ms
StringReplaceBenchmark.testStringUtils                  thrpt    6    8399.156 ±  109.223  ops/ms
StringReplaceBenchmark.testStringUtilsLong              thrpt    6     155.973 ±    2.731  ops/ms
StringReplaceBenchmark.testStringUtilsLongNoMatch       thrpt    6     358.311 ±    6.930  ops/ms
StringReplaceBenchmark.testStringUtilsNoMatch           thrpt    6  102227.568 ± 2394.978  ops/ms
```

Command

```
java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1  -bm thrpt,avgt -tu us
```

Result

```
Benchmark                                                Mode  Cnt    Score    Error   Units
StringReplaceBenchmark.testFast                         thrpt    6    9.217 ±  0.384  ops/us
StringReplaceBenchmark.testFastLong                     thrpt    6    0.150 ±  0.004  ops/us
StringReplaceBenchmark.testFastLongNoMatch              thrpt    6    0.326 ±  0.007  ops/us
StringReplaceBenchmark.testFastNoMatch                  thrpt    6   87.262 ±  1.077  ops/us
StringReplaceBenchmark.testLang3StringUtils             thrpt    6    6.980 ±  0.094  ops/us
StringReplaceBenchmark.testLang3StringUtilsLong         thrpt    6    0.151 ±  0.003  ops/us
StringReplaceBenchmark.testLang3StringUtilsLongNoMatch  thrpt    6    0.335 ±  0.004  ops/us
StringReplaceBenchmark.testLang3StringUtilsNoMatch      thrpt    6  101.451 ±  1.316  ops/us
StringReplaceBenchmark.testOsgl                         thrpt    6    8.637 ±  0.366  ops/us
StringReplaceBenchmark.testOsglLong                     thrpt    6    0.221 ±  0.006  ops/us
StringReplaceBenchmark.testOsglLongNoMatch              thrpt    6    0.502 ±  0.003  ops/us
StringReplaceBenchmark.testOsglNoMatch                  thrpt    6  103.398 ±  1.540  ops/us
StringReplaceBenchmark.testString                       thrpt    6    2.607 ±  0.203  ops/us
StringReplaceBenchmark.testStringLong                   thrpt    6    0.075 ±  0.002  ops/us
StringReplaceBenchmark.testStringLongNoMatch            thrpt    6    0.261 ±  0.004  ops/us
StringReplaceBenchmark.testStringNoMatch                thrpt    6    7.272 ±  0.165  ops/us
StringReplaceBenchmark.testStringUtils                  thrpt    6    8.054 ±  0.255  ops/us
StringReplaceBenchmark.testStringUtilsLong              thrpt    6    0.155 ±  0.004  ops/us
StringReplaceBenchmark.testStringUtilsLongNoMatch       thrpt    6    0.361 ±  0.007  ops/us
StringReplaceBenchmark.testStringUtilsNoMatch           thrpt    6  102.746 ±  2.171  ops/us
StringReplaceBenchmark.testFast                          avgt    6    0.107 ±  0.005   us/op
StringReplaceBenchmark.testFastLong                      avgt    6    6.756 ±  0.164   us/op
StringReplaceBenchmark.testFastLongNoMatch               avgt    6    2.985 ±  0.072   us/op
StringReplaceBenchmark.testFastNoMatch                   avgt    6    0.010 ±  0.001   us/op
StringReplaceBenchmark.testLang3StringUtils              avgt    6    0.142 ±  0.003   us/op
StringReplaceBenchmark.testLang3StringUtilsLong          avgt    6    6.622 ±  0.266   us/op
StringReplaceBenchmark.testLang3StringUtilsLongNoMatch   avgt    6    2.998 ±  0.048   us/op
StringReplaceBenchmark.testLang3StringUtilsNoMatch       avgt    6    0.010 ±  0.001   us/op
StringReplaceBenchmark.testOsgl                          avgt    6    0.115 ±  0.002   us/op
StringReplaceBenchmark.testOsglLong                      avgt    6    4.560 ±  0.055   us/op
StringReplaceBenchmark.testOsglLongNoMatch               avgt    6    1.996 ±  0.039   us/op
StringReplaceBenchmark.testOsglNoMatch                   avgt    6    0.010 ±  0.001   us/op
StringReplaceBenchmark.testString                        avgt    6    0.382 ±  0.039   us/op
StringReplaceBenchmark.testStringLong                    avgt    6   13.498 ±  0.400   us/op
StringReplaceBenchmark.testStringLongNoMatch             avgt    6    3.797 ±  0.069   us/op
StringReplaceBenchmark.testStringNoMatch                 avgt    6    0.137 ±  0.003   us/op
StringReplaceBenchmark.testStringUtils                   avgt    6    0.119 ±  0.003   us/op
StringReplaceBenchmark.testStringUtilsLong               avgt    6    6.548 ±  0.140   us/op
StringReplaceBenchmark.testStringUtilsLongNoMatch        avgt    6    2.983 ±  0.069   us/op
StringReplaceBenchmark.testStringUtilsNoMatch            avgt    6    0.010 ±  0.001   us/op
```

Command

```
java -jar target/benchmarks.jar  StringReplaceBenchmark -wi 3 -i 6 -f 1 -prof gc
```

```
Benchmark                                                                          Mode  Cnt          Score         Error   Units
StringReplaceBenchmark.testFast                                                   thrpt    6    9507629.408 ±  257231.050   ops/s
StringReplaceBenchmark.testFast:·gc.alloc.rate                                    thrpt    6       1063.643 ±      28.741  MB/sec
StringReplaceBenchmark.testFast:·gc.alloc.rate.norm                               thrpt    6        176.000 ±       0.001    B/op
StringReplaceBenchmark.testFast:·gc.churn.PS_Eden_Space                           thrpt    6       1059.443 ±     369.876  MB/sec
StringReplaceBenchmark.testFast:·gc.churn.PS_Eden_Space.norm                      thrpt    6        175.270 ±      60.127    B/op
StringReplaceBenchmark.testFast:·gc.churn.PS_Survivor_Space                       thrpt    6          0.090 ±       0.151  MB/sec
StringReplaceBenchmark.testFast:·gc.churn.PS_Survivor_Space.norm                  thrpt    6          0.015 ±       0.025    B/op
StringReplaceBenchmark.testFast:·gc.count                                         thrpt    6         19.000                counts
StringReplaceBenchmark.testFast:·gc.time                                          thrpt    6         31.000                    ms
StringReplaceBenchmark.testFastLong                                               thrpt    6     146077.646 ±    2555.991   ops/s
StringReplaceBenchmark.testFastLong:·gc.alloc.rate                                thrpt    6       3489.090 ±      61.106  MB/sec
StringReplaceBenchmark.testFastLong:·gc.alloc.rate.norm                           thrpt    6      37576.003 ±       0.001    B/op
StringReplaceBenchmark.testFastLong:·gc.churn.PS_Eden_Space                       thrpt    6       3488.876 ±     600.472  MB/sec
StringReplaceBenchmark.testFastLong:·gc.churn.PS_Eden_Space.norm                  thrpt    6      37571.855 ±    6331.491    B/op
StringReplaceBenchmark.testFastLong:·gc.churn.PS_Survivor_Space                   thrpt    6          0.090 ±       0.088  MB/sec
StringReplaceBenchmark.testFastLong:·gc.churn.PS_Survivor_Space.norm              thrpt    6          0.972 ±       0.948    B/op
StringReplaceBenchmark.testFastLong:·gc.count                                     thrpt    6         32.000                counts
StringReplaceBenchmark.testFastLong:·gc.time                                      thrpt    6         57.000                    ms
StringReplaceBenchmark.testFastLongNoMatch                                        thrpt    6     330830.516 ±    5232.472   ops/s
StringReplaceBenchmark.testFastLongNoMatch:·gc.alloc.rate                         thrpt    6         ≈ 10⁻⁴                MB/sec
StringReplaceBenchmark.testFastLongNoMatch:·gc.alloc.rate.norm                    thrpt    6          0.001 ±       0.001    B/op
StringReplaceBenchmark.testFastLongNoMatch:·gc.count                              thrpt    6            ≈ 0                counts
StringReplaceBenchmark.testFastNoMatch                                            thrpt    6   96282586.637 ± 1229613.367   ops/s
StringReplaceBenchmark.testFastNoMatch:·gc.alloc.rate                             thrpt    6         ≈ 10⁻⁴                MB/sec
StringReplaceBenchmark.testFastNoMatch:·gc.alloc.rate.norm                        thrpt    6         ≈ 10⁻⁵                  B/op
StringReplaceBenchmark.testFastNoMatch:·gc.count                                  thrpt    6            ≈ 0                counts
StringReplaceBenchmark.testLang3StringUtils                                       thrpt    6    6574272.605 ±  165992.655   ops/s
StringReplaceBenchmark.testLang3StringUtils:·gc.alloc.rate                        thrpt    6       1136.658 ±      28.692  MB/sec
StringReplaceBenchmark.testLang3StringUtils:·gc.alloc.rate.norm                   thrpt    6        272.000 ±       0.001    B/op
StringReplaceBenchmark.testLang3StringUtils:·gc.churn.PS_Eden_Space               thrpt    6       1245.714 ±    1051.241  MB/sec
StringReplaceBenchmark.testLang3StringUtils:·gc.churn.PS_Eden_Space.norm          thrpt    6        298.303 ±     253.662    B/op
StringReplaceBenchmark.testLang3StringUtils:·gc.churn.PS_Survivor_Space           thrpt    6          0.052 ±       0.142  MB/sec
StringReplaceBenchmark.testLang3StringUtils:·gc.churn.PS_Survivor_Space.norm      thrpt    6          0.012 ±       0.034    B/op
StringReplaceBenchmark.testLang3StringUtils:·gc.count                             thrpt    6         10.000                counts
StringReplaceBenchmark.testLang3StringUtils:·gc.time                              thrpt    6         15.000                    ms
StringReplaceBenchmark.testLang3StringUtilsLong                                   thrpt    6     148275.430 ±    1900.805   ops/s
StringReplaceBenchmark.testLang3StringUtilsLong:·gc.alloc.rate                    thrpt    6       3593.826 ±      45.425  MB/sec
StringReplaceBenchmark.testLang3StringUtilsLong:·gc.alloc.rate.norm               thrpt    6      38128.003 ±       0.001    B/op
StringReplaceBenchmark.testLang3StringUtilsLong:·gc.churn.PS_Eden_Space           thrpt    6       3558.193 ±     691.545  MB/sec
StringReplaceBenchmark.testLang3StringUtilsLong:·gc.churn.PS_Eden_Space.norm      thrpt    6      37748.872 ±    7263.184    B/op
StringReplaceBenchmark.testLang3StringUtilsLong:·gc.churn.PS_Survivor_Space       thrpt    6          0.097 ±       0.071  MB/sec
StringReplaceBenchmark.testLang3StringUtilsLong:·gc.churn.PS_Survivor_Space.norm  thrpt    6          1.032 ±       0.758    B/op
StringReplaceBenchmark.testLang3StringUtilsLong:·gc.count                         thrpt    6         48.000                counts
StringReplaceBenchmark.testLang3StringUtilsLong:·gc.time                          thrpt    6         55.000                    ms
StringReplaceBenchmark.testLang3StringUtilsLongNoMatch                            thrpt    6     348833.747 ±   21105.330   ops/s
StringReplaceBenchmark.testLang3StringUtilsLongNoMatch:·gc.alloc.rate             thrpt    6         ≈ 10⁻⁴                MB/sec
StringReplaceBenchmark.testLang3StringUtilsLongNoMatch:·gc.alloc.rate.norm        thrpt    6          0.001 ±       0.001    B/op
StringReplaceBenchmark.testLang3StringUtilsLongNoMatch:·gc.count                  thrpt    6            ≈ 0                counts
StringReplaceBenchmark.testLang3StringUtilsNoMatch                                thrpt    6  101905494.571 ±  939964.698   ops/s
StringReplaceBenchmark.testLang3StringUtilsNoMatch:·gc.alloc.rate                 thrpt    6         ≈ 10⁻⁴                MB/sec
StringReplaceBenchmark.testLang3StringUtilsNoMatch:·gc.alloc.rate.norm            thrpt    6         ≈ 10⁻⁵                  B/op
StringReplaceBenchmark.testLang3StringUtilsNoMatch:·gc.count                      thrpt    6            ≈ 0                counts
StringReplaceBenchmark.testOsgl                                                   thrpt    6    8740630.646 ±   90297.792   ops/s
StringReplaceBenchmark.testOsgl:·gc.alloc.rate                                    thrpt    6       1200.065 ±      12.364  MB/sec
StringReplaceBenchmark.testOsgl:·gc.alloc.rate.norm                               thrpt    6        216.000 ±       0.001    B/op
StringReplaceBenchmark.testOsgl:·gc.churn.PS_Eden_Space                           thrpt    6       1164.283 ±     901.012  MB/sec
StringReplaceBenchmark.testOsgl:·gc.churn.PS_Eden_Space.norm                      thrpt    6        209.614 ±     162.915    B/op
StringReplaceBenchmark.testOsgl:·gc.churn.PS_Survivor_Space                       thrpt    6          0.045 ±       0.145  MB/sec
StringReplaceBenchmark.testOsgl:·gc.churn.PS_Survivor_Space.norm                  thrpt    6          0.008 ±       0.026    B/op
StringReplaceBenchmark.testOsgl:·gc.count                                         thrpt    6         10.000                counts
StringReplaceBenchmark.testOsgl:·gc.time                                          thrpt    6         11.000                    ms
StringReplaceBenchmark.testOsglLong                                               thrpt    6     221726.764 ±    3902.016   ops/s
StringReplaceBenchmark.testOsglLong:·gc.alloc.rate                                thrpt    6       1780.345 ±      31.327  MB/sec
StringReplaceBenchmark.testOsglLong:·gc.alloc.rate.norm                           thrpt    6      12632.002 ±       0.001    B/op
StringReplaceBenchmark.testOsglLong:·gc.churn.PS_Eden_Space                       thrpt    6       1770.311 ±     700.117  MB/sec
StringReplaceBenchmark.testOsglLong:·gc.churn.PS_Eden_Space.norm                  thrpt    6      12564.665 ±    5046.848    B/op
StringReplaceBenchmark.testOsglLong:·gc.churn.PS_Survivor_Space                   thrpt    6          0.080 ±       0.094  MB/sec
StringReplaceBenchmark.testOsglLong:·gc.churn.PS_Survivor_Space.norm              thrpt    6          0.566 ±       0.664    B/op
StringReplaceBenchmark.testOsglLong:·gc.count                                     thrpt    6         21.000                counts
StringReplaceBenchmark.testOsglLong:·gc.time                                      thrpt    6         21.000                    ms
StringReplaceBenchmark.testOsglLongNoMatch                                        thrpt    6     502256.739 ±    8146.266   ops/s
StringReplaceBenchmark.testOsglLongNoMatch:·gc.alloc.rate                         thrpt    6         ≈ 10⁻⁴                MB/sec
StringReplaceBenchmark.testOsglLongNoMatch:·gc.alloc.rate.norm                    thrpt    6          0.001 ±       0.001    B/op
StringReplaceBenchmark.testOsglLongNoMatch:·gc.count                              thrpt    6            ≈ 0                counts
StringReplaceBenchmark.testOsglNoMatch                                            thrpt    6  103276343.019 ± 2092464.316   ops/s
StringReplaceBenchmark.testOsglNoMatch:·gc.alloc.rate                             thrpt    6         ≈ 10⁻⁴                MB/sec
StringReplaceBenchmark.testOsglNoMatch:·gc.alloc.rate.norm                        thrpt    6         ≈ 10⁻⁵                  B/op
StringReplaceBenchmark.testOsglNoMatch:·gc.count                                  thrpt    6            ≈ 0                counts
StringReplaceBenchmark.testString                                                 thrpt    6    2638292.353 ±   65681.341   ops/s
StringReplaceBenchmark.testString:·gc.alloc.rate                                  thrpt    6       1583.044 ±      39.416  MB/sec
StringReplaceBenchmark.testString:·gc.alloc.rate.norm                             thrpt    6        944.000 ±       0.001    B/op
StringReplaceBenchmark.testString:·gc.churn.PS_Eden_Space                         thrpt    6       1676.331 ±     516.746  MB/sec
StringReplaceBenchmark.testString:·gc.churn.PS_Eden_Space.norm                    thrpt    6        999.814 ±     311.491    B/op
StringReplaceBenchmark.testString:·gc.churn.PS_Survivor_Space                     thrpt    6          0.059 ±       0.057  MB/sec
StringReplaceBenchmark.testString:·gc.churn.PS_Survivor_Space.norm                thrpt    6          0.035 ±       0.034    B/op
StringReplaceBenchmark.testString:·gc.count                                       thrpt    6         15.000                counts
StringReplaceBenchmark.testString:·gc.time                                        thrpt    6         14.000                    ms
StringReplaceBenchmark.testStringLong                                             thrpt    6      66672.461 ±    1502.494   ops/s
StringReplaceBenchmark.testStringLong:·gc.alloc.rate                              thrpt    6       2606.843 ±      58.844  MB/sec
StringReplaceBenchmark.testStringLong:·gc.alloc.rate.norm                         thrpt    6      61512.007 ±       0.001    B/op
StringReplaceBenchmark.testStringLong:·gc.churn.PS_Eden_Space                     thrpt    6       2589.409 ±     806.319  MB/sec
StringReplaceBenchmark.testStringLong:·gc.churn.PS_Eden_Space.norm                thrpt    6      61075.297 ±   18279.463    B/op
StringReplaceBenchmark.testStringLong:·gc.churn.PS_Survivor_Space                 thrpt    6          0.100 ±       0.123  MB/sec
StringReplaceBenchmark.testStringLong:·gc.churn.PS_Survivor_Space.norm            thrpt    6          2.355 ±       2.940    B/op
StringReplaceBenchmark.testStringLong:·gc.count                                   thrpt    6         41.000                counts
StringReplaceBenchmark.testStringLong:·gc.time                                    thrpt    6         39.000                    ms
StringReplaceBenchmark.testStringLongNoMatch                                      thrpt    6     262992.187 ±    3782.514   ops/s
StringReplaceBenchmark.testStringLongNoMatch:·gc.alloc.rate                       thrpt    6        201.940 ±       2.895  MB/sec
StringReplaceBenchmark.testStringLongNoMatch:·gc.alloc.rate.norm                  thrpt    6       1208.002 ±       0.001    B/op
StringReplaceBenchmark.testStringLongNoMatch:·gc.churn.PS_Eden_Space              thrpt    6        200.486 ±     175.060  MB/sec
StringReplaceBenchmark.testStringLongNoMatch:·gc.churn.PS_Eden_Space.norm         thrpt    6       1200.451 ±    1060.031    B/op
StringReplaceBenchmark.testStringLongNoMatch:·gc.churn.PS_Survivor_Space          thrpt    6          0.191 ±       1.004  MB/sec
StringReplaceBenchmark.testStringLongNoMatch:·gc.churn.PS_Survivor_Space.norm     thrpt    6          1.139 ±       5.975    B/op
StringReplaceBenchmark.testStringLongNoMatch:·gc.count                            thrpt    6          9.000                counts
StringReplaceBenchmark.testStringLongNoMatch:·gc.time                             thrpt    6         11.000                    ms
StringReplaceBenchmark.testStringNoMatch                                          thrpt    6    5877538.796 ±   78720.009   ops/s
StringReplaceBenchmark.testStringNoMatch:·gc.alloc.rate                           thrpt    6       2211.622 ±      29.616  MB/sec
StringReplaceBenchmark.testStringNoMatch:·gc.alloc.rate.norm                      thrpt    6        592.000 ±       0.001    B/op
StringReplaceBenchmark.testStringNoMatch:·gc.churn.PS_Eden_Space                  thrpt    6       2291.496 ±     444.533  MB/sec
StringReplaceBenchmark.testStringNoMatch:·gc.churn.PS_Eden_Space.norm             thrpt    6        613.454 ±     122.465    B/op
StringReplaceBenchmark.testStringNoMatch:·gc.churn.PS_Survivor_Space              thrpt    6          0.090 ±       0.095  MB/sec
StringReplaceBenchmark.testStringNoMatch:·gc.churn.PS_Survivor_Space.norm         thrpt    6          0.024 ±       0.025    B/op
StringReplaceBenchmark.testStringNoMatch:·gc.count                                thrpt    6         33.000                counts
StringReplaceBenchmark.testStringNoMatch:·gc.time                                 thrpt    6         28.000                    ms
StringReplaceBenchmark.testStringUtils                                            thrpt    6    8096507.978 ±  146092.935   ops/s
StringReplaceBenchmark.testStringUtils:·gc.alloc.rate                             thrpt    6       1399.813 ±      25.264  MB/sec
StringReplaceBenchmark.testStringUtils:·gc.alloc.rate.norm                        thrpt    6        272.000 ±       0.001    B/op
StringReplaceBenchmark.testStringUtils:·gc.churn.PS_Eden_Space                    thrpt    6       1454.081 ±    1243.149  MB/sec
StringReplaceBenchmark.testStringUtils:·gc.churn.PS_Eden_Space.norm               thrpt    6        282.338 ±     240.523    B/op
StringReplaceBenchmark.testStringUtils:·gc.churn.PS_Survivor_Space                thrpt    6          0.052 ±       0.080  MB/sec
StringReplaceBenchmark.testStringUtils:·gc.churn.PS_Survivor_Space.norm           thrpt    6          0.010 ±       0.016    B/op
StringReplaceBenchmark.testStringUtils:·gc.count                                  thrpt    6         13.000                counts
StringReplaceBenchmark.testStringUtils:·gc.time                                   thrpt    6         11.000                    ms
StringReplaceBenchmark.testStringUtilsLong                                        thrpt    6     153164.208 ±    4571.822   ops/s
StringReplaceBenchmark.testStringUtilsLong:·gc.alloc.rate                         thrpt    6       3712.087 ±     110.837  MB/sec
StringReplaceBenchmark.testStringUtilsLong:·gc.alloc.rate.norm                    thrpt    6      38128.003 ±       0.001    B/op
StringReplaceBenchmark.testStringUtilsLong:·gc.churn.PS_Eden_Space                thrpt    6       3708.625 ±     736.179  MB/sec
StringReplaceBenchmark.testStringUtilsLong:·gc.churn.PS_Eden_Space.norm           thrpt    6      38078.187 ±    6695.783    B/op
StringReplaceBenchmark.testStringUtilsLong:·gc.churn.PS_Survivor_Space            thrpt    6          0.125 ±       0.111  MB/sec
StringReplaceBenchmark.testStringUtilsLong:·gc.churn.PS_Survivor_Space.norm       thrpt    6          1.285 ±       1.158    B/op
StringReplaceBenchmark.testStringUtilsLong:·gc.count                              thrpt    6         46.000                counts
StringReplaceBenchmark.testStringUtilsLong:·gc.time                               thrpt    6         47.000                    ms
StringReplaceBenchmark.testStringUtilsLongNoMatch                                 thrpt    6     334975.849 ±    5601.871   ops/s
StringReplaceBenchmark.testStringUtilsLongNoMatch:·gc.alloc.rate                  thrpt    6         ≈ 10⁻⁴                MB/sec
StringReplaceBenchmark.testStringUtilsLongNoMatch:·gc.alloc.rate.norm             thrpt    6          0.001 ±       0.001    B/op
StringReplaceBenchmark.testStringUtilsLongNoMatch:·gc.count                       thrpt    6            ≈ 0                counts
StringReplaceBenchmark.testStringUtilsNoMatch                                     thrpt    6  101780186.632 ± 1782431.242   ops/s
StringReplaceBenchmark.testStringUtilsNoMatch:·gc.alloc.rate                      thrpt    6         ≈ 10⁻⁴                MB/sec
StringReplaceBenchmark.testStringUtilsNoMatch:·gc.alloc.rate.norm                 thrpt    6         ≈ 10⁻⁵                  B/op
StringReplaceBenchmark.testStringUtilsNoMatch:·gc.count                           thrpt    6            ≈ 0                counts
```


## References

* http://openjdk.java.net/projects/code-tools/jmh
* http://java-performance.info/jmh/
* http://java-performance.info/introduction-jmh-profilers/
* http://hg.openjdk.java.net/code-tools/jmh/file/tip/jmh-samples/src/main/java/org/openjdk/jmh/samples/
* https://github.com/greenlaw110/Benchmark4StringReplace/blob/master/src/main/java/sample/StringReplaceBenchmark.java
