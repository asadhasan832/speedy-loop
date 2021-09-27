## Introduction
This is an implementation of the SPEEDY LOOP problem for JAVA 8 or higher. Please follow the following instructions to compile and execute, acommpanied by notes highligthing implementation limitations.

Submission by: Asad Hasan

## Design Details
- This solution implements an object oriented graph with vertex that reference edge objects which in-turn reference vertices.
- The route distance problems are solved by iterating along the path provided in a list, and calculated edge weights along the way, or returning a negative sentinel to indicate that the path is not found, which is converted to a string for verbosity.
- The number of trip problems are solved by recursively iterating over all possible routes starting from the starting town, counting all routes as a trip that end on the ending town, uptill the maximum number of stops is reached.
- The shortest path problems are solved using Dijkstra's algorithm, which is implemented as a singleton design pattern. The shortest distance to the same starting town is calculated by finding the minimum sum amognst the shortest paths to neighboring town and back.
- The number of different routes problem that are less than a given distance are solved by recursively iterating over all possible route from the starting town, counting all routes as a countable route that ends on the ending town, as long as the total distance covered reaching that town does not exceed the total allowed distance.


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
