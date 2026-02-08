import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeAll
    static void preTestingSettings(){
        baseUrl = "https://ru.wikipedia.org/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.remote  = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        open(baseUrl);
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void stayBrowser() {
        Configuration.holdBrowserOpen = true;
    }
}