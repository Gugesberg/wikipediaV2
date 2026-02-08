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
        WebDriverManager.firefoxdriver().setup();
        Configuration.browser = "firefox";
        baseUrl = "https://ru.wikipedia.org/";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        System.setProperty("selenide.chromeOptionsArgs",
                "--no-sandbox,--disable-dev-shm-usage,--disable-gpu,--disable-extensions," +
                        "--disable-plugins,--disable-images,--window-size=1920,1080");

        Configuration.timeout = 20_000;
        open(baseUrl);
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void stayBrowser() {
        Configuration.holdBrowserOpen = true;
    }
}