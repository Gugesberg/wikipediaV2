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

}