package com.example.framework.api;

import io.restassured.builder.RequestSpecBuilder;

public class BaseSpecs {
    public static RequestSpecBuilder get() {
        return new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .setBaseUri(Config.BASE_URL);
    }
}

