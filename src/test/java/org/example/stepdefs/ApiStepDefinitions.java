package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ApiStepDefinitions {

    private RequestSpecification request;
    private Response response;
    private String baseUrl;
    private JsonObject requestBodyJson; // Change to JsonObject for Gson flexibility
    private int postId; // New field to store the ID of the created post

    @Given("the API base URL is {string}")
    public void setApiBaseUrl(String url) {
        this.baseUrl = url;
        RestAssured.baseURI = baseUrl;
        request = RestAssured.given();
    }

    @Given("I have a request body with title {string} and body {string} and userId {int}")
    public void setRequestBodyForPost(String title, String body, int userId) {
        Gson gson = new Gson();
        requestBodyJson = new JsonObject();
        requestBodyJson.addProperty("title", title);
        requestBodyJson.addProperty("body", body);
        requestBodyJson.addProperty("userId", userId);

        request.header("Content-Type", "application/json");
        request.body(gson.toJson(requestBodyJson));
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String path) {
        response = request.get(path);
    }

    @When("I send a POST request to {string}")
    public void sendPostRequest(String path) {
        response = request.post(path);
    }

    // New When step to send a GET request to the previously created post
    @When("I send a GET request to the created post")
    public void sendGetRequestToCreatedPost() {
        if (this.postId == 0) {
            throw new IllegalStateException("No post ID was captured from a previous POST request. Ensure the POST scenario runs first.");
        }
        // Construct the URL using the captured postId
        response = request.get("/posts/" + this.postId);
        System.out.println("GET Response for created post: " + response.body().asString());
    }

    @Then("the response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode(),
                "Expected status code " + expectedStatusCode + " but got " + response.getStatusCode());

        System.out.println("Response: "+ response.body().asString()); // Use .asString() for better logging
    }

    @Then("the response should contain {string} with value {int}")
    public void verifyResponseContainsInt(String jsonPath, int expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    @Then("the response should contain {string} with value {string}")
    public void verifyResponseContainsString(String jsonPath, String expectedValue) {
        response.then().body(jsonPath, equalTo(expectedValue));
    }

    @Then("the response should contain a non-null {string}")
    public void verifyResponseContainsNonNull(String jsonPath) {
        response.then().body(jsonPath, notNullValue());
        // If the non-null field is "id", capture its value for subsequent GET requests
        if ("id".equals(jsonPath)) {
            this.postId = response.jsonPath().getInt(jsonPath);
            System.out.println("Captured postId: " + postId);
        }
    }

    // New Then step to verify the ID of the fetched post matches the captured ID
    @Then("the response should contain {string} with the captured post ID")
    public void verifyResponseContainsCapturedPostId(String jsonPath) {
        response.then().body(jsonPath, equalTo(this.postId));
    }
}
