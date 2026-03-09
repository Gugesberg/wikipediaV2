import com.github.javafaker.Faker;
import helpers.Attach;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static pages.LoginPage.validPassword;
import static pages.LoginPage.validUserName;

public class LoginTests extends BaseTest
{
    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    Faker faker = new Faker();
    String fakeLogin = faker.name().username();
    String fakePassword = faker.internet().password();

    @AfterEach
    void makeAttachments() {
        Attach.screenshotAs("Last sreenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }


    @Test
    @Disabled("проблемы с прохождением капчи")
    @Owner("AlexIvanov")
    @DisplayName("Успешная авторизация")
    void succsessAuthorization() {
        step("Нажимаем на кнопку 'Войти'" , () -> {
            mainPage.pushLoginLink();
        });
        step("Вводим существующее имя пользователя" , () -> {
            loginPage.setUserName(validUserName);
        });
        step("Вводим корректный пароль" , () -> {
            loginPage.setPassword(validPassword);
        });
        step("Прожимаем чек-бокс 'Оставаться в системе'" , () -> {
            loginPage.clickStayInSystemCheckBox();
        });
        step("Нажимаем на кнопку 'Войти'" , () -> {
            loginPage.clickSubmitButton();
        });

        step("Проверяем отображение логина" , () -> {
            $("#pt-userpage").shouldHave(text(validUserName));
        });


    }
    @Test
    @Owner("AlexIvanov")
    @DisplayName("Попытка авторизации с неверным паролем")
    void authorizationWithIncorrectPassword() {
        step("Нажимаем на кнопку 'Войти'" , () -> {
            mainPage.pushLoginLink();
        });
        step("Вводим существующее имя пользователя" , () -> {
            loginPage.setUserName(validUserName);
        });
        step("Вводим сгенерированный пароль" , () -> {
            loginPage.setPassword(fakePassword);
        });
        step("Прожимаем чек-бокс 'Оставаться в системе'" , () -> {
            loginPage.clickStayInSystemCheckBox();
        });
        step("Нажимаем на кнопку 'Войти'" , () -> {
            loginPage.clickSubmitButton();
        });
        step("Проверяем сообщение об ошибке в появившемся окне" , () -> {
            $(".cdx-message__content").shouldHave(text("Введены неверные имя участника или пароль."));
        });

    }
    @Test
    @Owner("AlexIvanov")
    @DisplayName("Попытка авторизации с неверным логином")
    void authorizationauthorizationWithIncorrectUserName() {
        step("Нажимаем на кнопку 'Войти'" , () -> {
            mainPage.pushLoginLink();
        });
        step("Вводим сгенерированное имя пользователя" , () -> {
            loginPage.setUserName(fakeLogin);
        });
        step("Вводим корректный пароль" , () -> {
            loginPage.setPassword(validPassword);
        });
        step("Прожимаем чек-бокс 'Оставаться в системе'" , () -> {
            loginPage.clickStayInSystemCheckBox();
        });
        step("Нажимаем на кнопку 'Войти'" , () -> {
            loginPage.clickSubmitButton();
        });
        step("Проверяем сообщение об ошибке в появившемся окне" , () -> {
            $(".cdx-message__content").shouldHave(text("Введены неверные имя участника или пароль."));
        });
    }

    public static class PushkinSearchTest {

        @Test
        void chechExtract(){
            get("https://ru.wikipedia.org/api/rest_v1/page/summary/%D0%9F%D1%83%D1%88%D0%BA%D0%B8%D0%BD?redirect=false")
                    .then()
                    .statusCode(200)
                    .log().all()
                    .body("description",equalTo("русский поэт, драматург и прозаик (1799–1837)"));

        }
    }
}