package ApiTests;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PushkinSearchTest {

    @Test
    void chechExtract(){
        get("https://ru.wikipedia.org/api/rest_v1/page/summary/%D0%9F%D1%83%D1%88%D0%BA%D0%B8%D0%BD?redirect=false")
                .then()
                .statusCode(200)
                .body("descriptio",equalTo("русский поэт, драматург и прозаик (1799–1837)"));

    }
}