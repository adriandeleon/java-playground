package com.grokthecode.tutorials.java8.winterbe;

import lombok.val;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
public class StreamTutorial {

    public void example1() {
        val myList = List.of("a1", "a2", "b1", "c2", "c1");

        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    public void example2(){
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .isPresent();
    }

    public void example3(){
        IntStream.range(1, 4).forEach(System.out::println);
    }
}
