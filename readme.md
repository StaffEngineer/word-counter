# Word Counter

## Description

This is a simple word counter that counts the number of words in a given file and outputs first N most frequent words in the file. N words is configurable via `PRINT_AMOUNT` environment variable (if omitted, prints all words).

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

## Testing

_Tested on Java 21 and Maven 3.8.4 on MacOS._

The solution was tested on 20GB file. It took around around 5 mins to process it on M1 Macbook Pro. Further improvements can be made by parallelizing the processing of the file. One of the options is to split the file into chunks and process them in parrallel, then aggregrate result. Chunk amount should correspond to the number of available cores. 