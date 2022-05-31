package tests;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenoidTests {

    @Test
    void checkTotal() {
        given()
                .when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .body("total", is(20));
    }

    @Test
    void checkTotalWithoutGiven() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("chrome", is(100.0));
    }

    @Test
    void checkChromeVersion() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .body("browsers.chrome", hasKey("100.0"));
    }

    @Test
    void checkTotalBadPractice(){
        String response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().response().asString();

        System.out.println("Response: " + response);

        String expectedResponse = "{\"total\":20,\"used\":0,\"queued\":0," +
                "\"pending\":0,\"browsers\":{\"chrome\":{\"100.0\":{}," +
                "\"99.0\":{}},\"firefox\":{\"97.0\":{}," +
                "\"98.0\":{}},\"opera\":{\"84.0\":{},\"85.0\":{}}}}\n";
        assertEquals(expectedResponse, response);
    }
    @Test
    void checkTotalGoodPractice(){
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .extract().path("total");

        System.out.println("Response: " + response);

        Integer expectedResponse = 20;
        assertEquals(expectedResponse, response);
    }

}
