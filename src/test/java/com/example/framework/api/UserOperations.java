package com.example.framework.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


public class UserOperations {
    public static Response listUsersInPage(int pageNumber) {
        return given().filter(new AllureRestAssured()).spec(BaseSpecs.get().build()).when().get(String.format("?page=%d", pageNumber));
    }

    public static Response updateUser(String newUserData, int userId) {
        return given().filter(new AllureRestAssured()).spec(BaseSpecs.get().build()).body(newUserData).when().put(String.format("/%d", userId));
    }

    public static Response createNewUser(String newUserData) {
        return given().filter(new AllureRestAssured()).spec(BaseSpecs.get().build()).body(newUserData).when().post();
    }
}
