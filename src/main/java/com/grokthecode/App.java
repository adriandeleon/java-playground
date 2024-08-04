package com.grokthecode;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Validate;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.*;

@Log4j2
@NullMarked
public class App {
    public static final String FIRST_NAME_CANNOT_BE_NULL = "firstName cannot be null.";
    public static final String LAST_NAME_CANNOT_BE_NULL = "lastName cannot be null.";

    public static void main(String[] args) {
        log.info("Hello World");
        Map<Integer, String> myMap = new HashMap<>();
        myMap.put(1, "Adrian");

        System.out.println("name: " + myMap.get(1));

        System.out.println("1");

        final List<String> myList = List.of("Adrian", "Adriancito", "Gisel");

        myList.forEach(System.out::println);
    }

    public void checkParamsWithRequireNonNull(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName, FIRST_NAME_CANNOT_BE_NULL);
        Objects.requireNonNull(lastName, LAST_NAME_CANNOT_BE_NULL);
        Validate.notNull(firstName, FIRST_NAME_CANNOT_BE_NULL);

        Validate.notNull(lastName, LAST_NAME_CANNOT_BE_NULL);

        log.info(String.format("firstName: %s", firstName));
        log.info(String.format("lastName: %s", lastName));
    }

    public void checkParamsWithAnnotation(final @NonNull String firstName, @NonNull final String lastName) {
        log.info(String.format("firstName: %s", firstName));
        log.info(String.format("lastName: %s", lastName));
    }
}
