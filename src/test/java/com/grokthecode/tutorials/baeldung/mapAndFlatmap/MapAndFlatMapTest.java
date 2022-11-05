package com.grokthecode.tutorials.baeldung.mapAndFlatmap;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

//https://www.baeldung.com/java-difference-map-and-flatmap
public class MapAndFlatMapTest {

    @Test
    void mapInOptionalTest1() {
        Optional<String> s = Optional.of("test");
        assertEquals(Optional.of("TEST"), s.map(String::toUpperCase));
    }

    @Test
    void mapInOptionalTest2() {
        assertEquals(Optional.of(Optional.of("STRING")),
                Optional
                        .of("string")
                        .map(s -> Optional.of("STRING")));
    }

    @Test
    void flatMapOptional1() {
        assertEquals(Optional.of("STRING"), Optional
                .of("string")
                .flatMap(s -> Optional.of("STRING")));
    }
}
