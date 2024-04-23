package org.example.utils;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredUtils {

    public static Response GET(String apiBaseUrl, Map<String, Object> headerValue) {
        if (apiBaseUrl.isEmpty() || headerValue.isEmpty())
            return null;

        try {
            return given()
                    .headers(headerValue).log().all()
                    .when()
                    .get(apiBaseUrl);
        } catch (Exception e) {
            // Log the error
            System.out.println("Exception occurred while performing GET request: " + e.getMessage());
            return null;
        }
    }
    }

