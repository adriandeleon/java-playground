package com.grokthecode.examples.restAssured;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class restAssured {

    public static void setup() {
        baseURI = "https://reqres.in/";
        basePath = "/api";
    }

    @Test
    public void getResponseBody(){
        given()
                .pathParam("id", 2)
                .when()
                .get("/users/{id}")
                .then()
                .statusCode(equalTo(200))
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"));
    }
}
