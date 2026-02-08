import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class   SearchTests extends BaseTest {
    MainPage mainPage = new MainPage();

    @Test
    @Owner("AlexIvanov")
    @DisplayName("Поиск статьи о Пушкине")
    void searchPageAboutPushkin(){
        step("Вбиваем в строку поиска текст 'Пушкин' и нажимаем на Enter" , () -> {
            mainPage.getSearch("Пушкин");
        });
        step("Проверяем оглавнение открывшейся страницы" , () -> {
            $(".mw-page-title-main").shouldHave(text("Пушкин, Александр Сергеевич"));
        });
    }

}