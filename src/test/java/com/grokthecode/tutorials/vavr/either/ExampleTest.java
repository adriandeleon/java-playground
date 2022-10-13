package com.grokthecode.tutorials.vavr.either;

import io.vavr.control.Either;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExampleTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void computeWithEitherTest_rightValue() {
        val either = computeWithEither(100);

        checkAndPrintEither(either);
    }
    
    @Test
    void computeWithEitherTest_leftValue() {
        val either = computeWithEither(50);

        checkAndPrintEither(either);
    }

    private static void checkAndPrintEither(Either<String, Integer> either) {
        if(either.isRight()){
            System.out.println(either.get());
        }else{
            System.out.println(either.getLeft());
        }
    }

    private static Either<String, Integer> computeWithEither(final int marks){
        if(marks < 85) {
            return Either.left("Marks not acceptable");
        }else{
            return Either.right(marks);
        }
    }
}
