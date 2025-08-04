package org.example.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PersonApiSteps {
    private RequestSpecification request;
    private Response response;
    private JsonObject requestPayload;
    private static final String BASE_URL = "https://688fa50cf21ab1769f89c5be.mockapi.io/api/v1";
    private static final String PERSON_SCHEMA = "{\n" +
            "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
            "  \"type\": \"object\",\n" +
            "  \"properties\": {\n" +
            "    \"id\": {\"type\": \"string\"},\n" +
            "    \"name\": {\"type\": \"string\"},\n" +
            "    \"age\": {\"type\": \"number\"},\n" +
            "    \"profession\": {\"type\": \"string\"},\n" +
            "    \"isAdmin\": {\"type\": \"boolean\"}\n" +  // Coma eliminada aquí
            "  },\n" +
            "  \"required\": [\"id\", \"name\", \"age\", \"profession\", \"isAdmin\"]\n" +
            "}";

    @Given("I set the base API URL")
    public void setBaseApiUrl() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");
    }

    @Given("I prepare a valid person payload with:")
    public void preparePersonPayload(Map<String, String> data) {
        requestPayload = new JsonObject();
        requestPayload.addProperty("name", data.get("name"));
        requestPayload.addProperty("age", Integer.parseInt(data.get("age")));
        requestPayload.addProperty("profession", data.get("profession"));
        requestPayload.addProperty("isAdmin", Boolean.parseBoolean(data.get("isAdmin")));
    }

    @When("I send a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        response = request.body(requestPayload.toString()).post(endpoint);
    }

    @When("I send a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = request.get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("the response should match the person schema")
    public void verifyResponseSchema() {
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(PERSON_SCHEMA));
    }

    @Then("the response should contain the created person details")
    public void verifyCreatedPersonDetails() {
        response.then()
                .body("name", equalTo(requestPayload.get("name").getAsString()))
                .body("age", equalTo(requestPayload.get("age").getAsInt()))
                .body("profession", equalTo(requestPayload.get("profession").getAsString()))
                .body("isAdmin", equalTo(requestPayload.get("isAdmin").getAsBoolean()))
                .body("id", notNullValue());
        // createdAt fue eliminado según el schema de la imagen
    }

    @Then("the response should be a non-empty array")
    public void verifyNonEmptyArrayResponse() {
        JsonArray persons = new Gson().fromJson(response.getBody().asString(), JsonArray.class);
        Assertions.assertTrue(persons.size() > 0, "Response array should not be empty");
    }

    @Then("each item in the array should match the person schema")
    public void verifyEachItemMatchesSchema() {
        JsonArray persons;
        try {
            persons = new Gson().fromJson(response.getBody().asString(), JsonArray.class);
        } catch (Exception e) {
            Assertions.fail("La respuesta no es un JSON array válido: " + response.getBody().asString());
            return;
        }

        for (JsonElement person : persons) {
            try {
                JsonSchemaValidator.matchesJsonSchema(PERSON_SCHEMA)
                        .matches(person.toString());
            } catch (Exception e) {
                Assertions.fail("El elemento no coincide con el schema de persona: " + person.toString() +
                        "\nError: " + e.getMessage());
            }
        }
    }
}