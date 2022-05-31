package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class RequresinTests {

    @Test
    void succefulLogin() {
            /*
            request: https://reqres.in/api/login

            data:
            {
                "email": "eve.holt@reqres.in",
                "password": "cityslicka"
            }

            response:

            {
                "token": "QpwL5tke4Pnpja7X4"
            }
            */

        String autorizedData = "{ \"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"cityslicka\" }";

        given()
                .body(autorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void negativeLogin(){
        String autorizedData = "{ \"email\": \"peter@klaven\" }";

        given()
                .body(autorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }
}
