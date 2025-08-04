package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {

    private static final String BASE_URL = "https://688fa50cf21ab1769f89c5be.mockapi.io/api/v1/person";

    public static void main(String[] args) {
        System.out.println("🚀 Iniciando pruebas de API con Rest Assured y Gson...\n");

        // Ejemplo de solicitud GET
        getExample();

        System.out.println("\n--------------------------------------------------\n");

        // Ejemplo de solicitud POST
        postExample();

        System.out.println("\n✅ Pruebas finalizadas.");
    }

    /**
     * Realiza una solicitud GET a un endpoint público y muestra la respuesta.
     */
    private static void getExample() {
        System.out.println("Realizando solicitud GET a: " + BASE_URL);

        Response response = RestAssured.given()
                .when()
                .get(BASE_URL)
                .then()
                .extract().response();

        System.out.println("GET - Código de estado: " + response.getStatusCode());
        System.out.println("GET - Cuerpo de la respuesta:");
        System.out.println(response.asPrettyString()); // Imprime el JSON formateado
    }

    /**
     * Realiza una solicitud POST a un endpoint público con un cuerpo JSON y muestra la respuesta.
     */
    private static void postExample() {
        System.out.println("Realizando solicitud POST a: " + BASE_URL);

        // Crear un objeto JSON para el cuerpo de la solicitud con Gson
        Gson gson = new Gson();
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", "Vaizhakaer");
        requestBody.addProperty("age", 23);
        requestBody.addProperty("profession", "Tester");
        requestBody.addProperty("isAdmin", false);

        Response response = RestAssured.given()
                .header("Content-Type", "application/json") // Importante para enviar JSON
                .body(gson.toJson(requestBody)) // Convertir el objeto Gson a String JSON
                .when()
                .post(BASE_URL)
                .then()
                .extract().response();

        System.out.println("POST - Codigo de estado: " + response.getStatusCode());
        System.out.println("POST - Cuerpo de la respuesta:");
        System.out.println(response.asPrettyString()); // Imprime el JSON formateado
    }
}