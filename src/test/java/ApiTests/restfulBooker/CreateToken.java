package apiTests.restfulBooker;

import models.AuthDataModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateToken {
    String BaseURL = "https://restful-booker.herokuapp.com/auth";

    @Test
    void postToken(){
        AuthDataModel authDataModel  = new AuthDataModel();
        authDataModel.setUsername("admin");
        authDataModel.setPassword("password123");
        given()
                .body(authDataModel)
                .contentType(JSON)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
               // .param("redirect", "false")
                .when()
                .post(BaseURL)
                .then()
                .log().all()
                .statusCode(200)
                .body("token",notNullValue());

    }

    @Test
    void postTokenWithBadCred(){
        AuthDataModel authDataModel  = new AuthDataModel();
        authDataModel.setUsername("sasha");
        authDataModel.setPassword("password");
        given()
                .body(authDataModel)
                .contentType(JSON)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                // .param("redirect", "false")
                .when()
                .post(BaseURL)
                .then()
                .log().all()
                .statusCode(200)
                .body("reason",equalTo("Bad credentials"));

    }
}

