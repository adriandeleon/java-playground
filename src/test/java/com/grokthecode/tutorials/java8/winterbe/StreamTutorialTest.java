package com.grokthecode.tutorials.java8.winterbe;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

//https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
class StreamTutorialTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void example1() {
        val myList = List.of("a1", "a2", "b1", "c2", "c1");

        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void example2() {
        Stream.of("a1", "a2", "a3")
                .findFirst()
                .isPresent();
    }

    @Test
    void example3() {
        IntStream.range(1, 4).forEach(System.out::println);
    }
}
