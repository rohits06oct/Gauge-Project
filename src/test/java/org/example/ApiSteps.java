package org.example;

import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiSteps {

    private Response response;

    @Step("Send GET request to endpoint <endpoint>")
    public void sendGetRequest(String endpoint) {
        response = given()
                .header("Content-Type", "application/json")
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    @Step("Verify the response status code is <statusCode>")
    public void verifyStatusCode(int statusCode) {
        assertThat("Unexpected status code", response.getStatusCode(), is(statusCode));
    }

    @Step("Verify the user ID is <userId>")
    public void verifyUserId(int userId) {
        int actualUserId = response.jsonPath().getInt("userId");
        assertThat("Unexpected userId", actualUserId, is(userId));
    }

    @Step("Verify the title is <expectedTitle>")
    public void verifyTitle(String expectedTitle) {
        String actualTitle = response.jsonPath().getString("title");
        assertThat("Unexpected title", actualTitle, is(expectedTitle));
    }
}
