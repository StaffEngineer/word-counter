# Word Counter

## Description

This is a simple word counter that counts the number of words in a given file and outputs first N most frequent words in the file (including frequency). N words is configurable via `PRINT_AMOUNT` environment variable (if omitted, prints all words).

## Basic Usage

```
PRINT_AMOUNT=10 mvn clean compile exec:java -Dexec.args="./t8.shakespeare.txt"
```

maven will compile the project and run it with the given file as an argument. The output will be the first 10 most frequent words in the file. Like this:

```
the: 27549
and: 26037
i: 19540
to: 18700
of: 18010
a: 14383
my: 12455
in: 10671
you: 10630
that: 10487
```

There is built jar file in the root of the project. It can be used to run the program without maven. Like this:

```java
PRINT_AMOUNT=10 java -cp wordcount.jar com.wordcount.Main "./t8.shakespeare.txt"
```

## Testing

_Tested on Java 21 and Maven 3.8.4 on MacOS._

The solution was tested on 20GB file (large than RAM). It took around around 8 mins to process it on M1 Macbook Pro. Further improvements can be made by parallelizing the processing of the large files. One of the options is to split the file into chunks and process them in parrallel, then aggregrate result. Chunk amount should correspond to the number of available cores for a single machine scenario. For testing purposes `split -n 4 large.txt` was used to split the file into 4 chunks and run 4 instances of the program in parrallel, it took around 2 mins to process 20GB of text stream. The solution accommodates lines of any length, so it can be used to process text streams of any size.

## Further Improvements

- Parallelize the processing of the file for large files
- Add testing library (e.g. JUnit) and write unit tests
- Add logging library (e.g. Log4j) and add logging
- Use command line library (e.g. Apache Commons CLI) to parse command line arguments