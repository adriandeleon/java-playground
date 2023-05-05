package com.grokthecode.tutorials.faker;

import net.datafaker.Faker;
import org.junit.jupiter.api.Test;

//https://www.datafaker.net/documentation/getting-started
public class FakerTest {

    @Test
    void fakerTest(){
        final Faker faker = new Faker();

        final String name = faker.name().fullName();
        final String firstName = faker.name().firstName();
        final String lastName = faker.name().lastName();

        final String streetAddress = faker.address().streetAddress();

        System.out.printf("name: %s \n", name);
        System.out.printf("firstName: %s \n", firstName);
        System.out.printf("lastName: %s \n", lastName);
        System.out.printf("streetAddress: %s \n", streetAddress);
    }
}
