import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void preTestingSettings(){
        baseUrl = "https://ru.wikipedia.org/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        open(baseUrl);
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void stayBrowser() {
        Configuration.holdBrowserOpen = true;
    }
}