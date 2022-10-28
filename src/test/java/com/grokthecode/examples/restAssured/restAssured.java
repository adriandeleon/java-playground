package com.grokthecode.examples.restAssured;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class restAssured {
    //https://howtodoinjava.com/java/library/rest-assured-tutorial/
    public static void setup() {
        baseURI = "https://httpbin.org/";
        //basePath = "/api";
    }

    @Disabled
    @Test
    public void getResponseBody(){
        given()
                .when()
                .get("https://httpbin.org/ip")
                .then()
                .assertThat().statusCode(200)
                .log().body()
                .body("origin", equalTo("189.203.90.251"));

        //System.out.println(response.jsonPath().prettyPrint());
    }
}
