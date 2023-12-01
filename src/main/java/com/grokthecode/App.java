package com.grokthecode;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.Validate;

import java.util.*;

@Log4j2
public class App {
    public static final String FIRST_NAME_CANNOT_BE_NULL = "firstName cannot be null.";
    public static final String LAST_NAME_CANNOT_BE_NULL = "lastName cannot be null.";

    public static void main(String[] args) {
        log.info("Hello World");
        Map<Integer, String> myMap = new HashMap<>();
        myMap.put(1, "Adrian");

        System.out.println("name: " + myMap.get(1));

        final boolean foo;
        System.out.println("1");
        foo = true;

        final List<String> myList = List.of("Adrian", "Adriancito", "Gisel");

        for (String s : myList) {

        }
    }

    public void checkParamsWithRequireNonNull(final String firstName, final String lastName) {
        Objects.requireNonNull(firstName, FIRST_NAME_CANNOT_BE_NULL);
        Objects.requireNonNull(lastName, LAST_NAME_CANNOT_BE_NULL);
        Validate.notNull(firstName);

        log.info(String.format("firstName: %s", firstName));
        log.info(String.format("lastName: %s", lastName));
    }

    public void checkParamsWithAnnotation(@NonNull final String firstName,
                                          @NonNull final String lastName) {
        log.info(String.format("firstName: %s", firstName));
        log.info(String.format("lastName: %s", lastName));
    }
}
