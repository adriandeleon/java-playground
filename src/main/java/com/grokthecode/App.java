package com.grokthecode;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@NullMarked
public class App {
    public static final String FIRST_NAME_CANNOT_BE_NULL = "firstName cannot be null.";
    public static final String LAST_NAME_CANNOT_BE_NULL = "lastName cannot be null.";
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(App.class);

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

        log.info("firstName: {}", firstName);
        log.info("lastName: {}", lastName);
    }

    public void checkParamsWithAnnotation(final @NonNull String firstName, @NonNull final String lastName) {
        log.info("firstName: {}", firstName);
        log.info("lastName: {}", lastName);
    }
}
