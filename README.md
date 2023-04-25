[![Java CI with Maven](https://github.com/adriandeleon/java-playground/actions/workflows/maven.yml/badge.svg)](https://github.com/adriandeleon/java-playground/actions/workflows/maven.yml)

# Java Playground: A Java testing grounds.

Adrian De Leon <adrian@adriandeleon.me>


```mermaid
flowchart LR

A[Hard] -->|Text| B(Round)
B --> C{Decision}
C -->|One| D[Result 1]
C -->|Two| E[Result 2]

```
Here is another diagram.

```mermaid
sequenceDiagram
Alice->>John: Hello John, how are you?
loop Healthcheck
    John->>John: Fight against hypochondria
end
Note right of John: Rational thoughts!
John-->>Alice: Great!
John->>Bob: How about you?
Bob-->>John: Jolly good!

```
Clean the project...
```shell
mvn clean
```

Compile the project...
```shell
mvn compile
```



