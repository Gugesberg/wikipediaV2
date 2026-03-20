package apiTests.restfulBooker;

import models.PostTokenRequestModel;
import models.PostTokenResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateToken {
    String BaseURL = "https://restful-booker.herokuapp.com/auth";

    @Test
    void postToken(){
        PostTokenRequestModel postTokenRequestModel = new PostTokenRequestModel();
        postTokenRequestModel.setUsername("admin");
        postTokenRequestModel.setPassword("password123");
        PostTokenResponseModel response = given()
                .body(postTokenRequestModel)
                .contentType(JSON)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .when()
                .post(BaseURL)
                .then()
                .log().all()
                .statusCode(200)
                .extract().as(PostTokenResponseModel.class);
        assertNotNull("token");

    }

    @Test
    void postTokenWithBadCred(){
        PostTokenRequestModel postTokenRequestModel = new PostTokenRequestModel();
        postTokenRequestModel.setUsername("sasha");
        postTokenRequestModel.setPassword("password");
        PostTokenResponseModel response = given()
                .body(postTokenRequestModel)
                .contentType(JSON)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .when()
                .post(BaseURL)
                .then()
                .log().all()
                .statusCode(200)
               .extract().as(PostTokenResponseModel.class);
        assertNotNull("reason");
        assertEquals("Bad credentials",response.getReason());

    }
}

