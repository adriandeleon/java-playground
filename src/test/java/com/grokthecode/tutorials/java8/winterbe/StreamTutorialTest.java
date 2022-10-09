package com.grokthecode.tutorials.java8.winterbe;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamTutorialTest {

    private StreamTutorial streamTutorial;

    @BeforeEach
    void setUp() {
        streamTutorial = new StreamTutorial();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void example1() {
        streamTutorial.example1();
    }

    @Test
    void example2() {
        streamTutorial.example2();
    }

    @Test
    void example3() {
        streamTutorial.example3();
    }
}
