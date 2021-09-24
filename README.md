## Introduction
This is an implementation of the SPEEDY LOOP problem in JAVA. Please follow the following instructions to compile and execute, acommpanied by notes highligthing implementation limitations.

## Implementation Limitations
- Current implementation can calculate total distances up to Long.MAX_VALUE (9223372036854775807 on a 64-bit machine).
- Certain methods make use of recursion, which uses a call stack proportional the input size. For really large inputs the Java flag '-Xss' can be used to increase limits of execution.

## Input format
- This implementation accepts input from a text file where town name pair and distance is expected to be on one line each.
- Example input file format:
```
AB5
BC4
CD8
DC8
DE6
AD5
CE2
EB3
AE7
```

## Compilation
- Make use of the `javac` command from the project directory in the following way to generate compiled `.class` files:
```bash
javac DijkstrasAlgorithm.java Graph.java Edge.java Vertex.java SpeedyLoop.java
```

## Execution
- Execute by running the `java` command from the project directory while supplying an input file name:
```bash
java SpeedyLoop inputs.txt
```