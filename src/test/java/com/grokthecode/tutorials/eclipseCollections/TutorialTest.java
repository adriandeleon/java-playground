package com.grokthecode.tutorials.eclipseCollections;

import lombok.val;
import org.eclipse.collections.api.tuple.Triple;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.tuple.Tuples;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TutorialTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void tripleTest(){

        val countries = Lists.immutable.of(
                Tuples.triple("Netherlands", 31, true),
                Tuples.triple("United Kingdom", 44, false),
                Tuples.triple("Germany", 49, true));

        val euCountries = countries
                .select(Triple::getThree)
                .collect(Triple::getOne);

        euCountries.forEach(System.out::println);

    }
}
