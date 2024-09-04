package com.example.Tests.api;

import com.example.framework.api.UserOperations;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

public class UserTests {

    private final Faker faker = new Faker();

    @DataProvider(name = "user-data")
    public Object[][] userData() {
        return new Object[][]{
                {"morpheus", "zion resident"},
                {faker.name().firstName(), faker.job().position()},
        };
    }

    @Test(dataProvider = "user-data")
    public void testCreateUser(String name, String job) {
        String requestBody = String.format("{\n" +
                "    \"name\": \"%s\",\n" +
                "    \"job\": \"%s\"\n" +
                "}", name, job);
        Response response = UserOperations.createNewUser(requestBody);

        assertEquals(response.statusCode(), 201);
        assertEquals(response.jsonPath().get("name"), name);
        assertEquals(response.jsonPath().get("job"), job);
        assertTrue(response.jsonPath().getInt("id") > 0);
        assertNotEquals(response.jsonPath().get("createdAt"), "");
        assertNotNull(response.jsonPath().get("createdAt"));
    }

    @Test(dataProvider = "user-data")
    public void testUpdateUser(String name, String job) {
        String requestBody = String.format("{\n" +
                "    \"name\": \"%s\",\n" +
                "    \"job\": \"%s\"\n" +
                "}", name, job);
        Response response = UserOperations.updateUser(requestBody, 2);

        assertEquals(response.statusCode(), 200);
        assertEquals(response.jsonPath().get("name"), name);
        assertEquals(response.jsonPath().get("job"), job);
        assertNotEquals(response.jsonPath().get("createdAt"), "");
        assertNotNull(response.jsonPath().get("updatedAt"));
    }

    @DataProvider(name = "users-page-2")
    public Object[][] usersPage2() {
        return new Object[][]{
                {7, "michael.lawson@reqres.in"},
                {8, "lindsay.ferguson@reqres.in"},
                {9, "tobias.funke@reqres.in"},
                {10, "byron.fields@reqres.in"},
                {11, "george.edwards@reqres.in"},
                {12, "rachel.howell@reqres.in"}
        };
    }

    @Test(dataProvider = "users-page-2")
    public void testListUsersInPageNo2(int id, String email) {
        Response response = UserOperations.listUsersInPage(2);

        assertEquals(response.statusCode(), 200);
        assertEquals(response.jsonPath().getInt("page"), 2);
        assertEquals(response.jsonPath().getInt("per_page"), 6);
        assertEquals(response.jsonPath().getInt("total"), 12);
        assertEquals(response.jsonPath().getInt("total_pages"), 2);

        List<Map<String, ?>> data = response.jsonPath().getList("data");
        assertEquals(data.size(), 6);

        for (Map<String, ?> user : data) {
            if ((int) user.get("id") == id) {
                assertEquals(user.get("email"), email);
                return;
            }
        }

        assertTrue(false, String.format("User with id %d not found in page 2", id));
    }
}


