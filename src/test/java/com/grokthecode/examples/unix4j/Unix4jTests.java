package com.grokthecode.examples.unix4j;

import org.junit.jupiter.api.Test;
import org.unix4j.Unix4j;


public class Unix4jTests {

    @Test
    void grepTest() {
        final String result = Unix4j.fromString("hello world").grep("hello").wc().toStringResult();
    }
}
