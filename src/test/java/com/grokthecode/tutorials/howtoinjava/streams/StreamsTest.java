package com.grokthecode.tutorials.howtoinjava.streams;

import org.junit.jupiter.api.Test;

import java.util.List;

//https://howtodoinjava.com/java8/stream-map-vs-flatmap/
public class StreamsTest {

    @Test
    void mapTest(){
        final List<String> listOfStrings = List.of("1", "2", "3", "4", "5");

        final List<Integer> listOfIntegers = listOfStrings
                .stream()
                .map(Integer::valueOf)
                .toList();

        System.out.printf(listOfIntegers.toString());
    }

    @Test
    void flatMapTest(){
        final List<Integer> list1 = List.of(1,2,3);
        final List<Integer> list2 = List.of(4,5,6);
        final List<Integer> list3 = List.of(7,8,9);

        final List<List<Integer>> listOfLists = List.of(list1, list2, list3);

        final List<Integer> listOfAllIntegers = listOfLists
                .stream()
                .flatMap(x -> x.stream())
                .toList();

        System.out.printf(listOfLists.toString() + "\n");
        System.out.printf(listOfAllIntegers.toString());
    }
}
