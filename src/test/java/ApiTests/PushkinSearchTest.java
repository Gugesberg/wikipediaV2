package apiTests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PushkinSearchTest {
    static String BaseURL = "https://ru.wikipedia.org/api/rest_v1";

    @Test
    void checkDescription(){
        given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .param("redirect", "false")
                .when()
                .get( BaseURL +"/page/summary/Пушкин?redirect=false")
                .then()
                .log().all()
                .statusCode(200)
                .body("description",equalTo("русский поэт, драматург и прозаик (1799–1837)"));

    }

    @Test
    void checkIncorrectSearch(){
        given()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .param("redirect", "false")
                .when()
                .get(BaseURL +"/page/summary/ывпаывпвав?redirect=false")
                .then()
                .log().all()
                .statusCode(404);


    }
}
