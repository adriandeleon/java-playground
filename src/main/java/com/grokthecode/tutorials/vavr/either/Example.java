package com.grokthecode.tutorials.vavr.either;

import io.vavr.control.Either;
import lombok.val;

//https://www.baeldung.com/vavr-either
public class Example {

    public static void main(String[] args) {

        val either = computeWithEither(100);

        if(either.isRight()){
            System.out.println(either.get());
        }else{
            System.out.println(either.getLeft());
        }
    }

    public static Either<String, Integer> computeWithEither(final int marks){
        if(marks < 85) {
            return Either.left("Marks not acceptable");
        }else{
            return Either.right(marks);
        }
    }
}
