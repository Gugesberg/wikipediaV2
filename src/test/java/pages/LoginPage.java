package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement userNameLocator = $("#wpName1"),
            passwordLocator = $("#wpPassword1"),
            submitButtonLocator = $("#wpLoginAttempt"),
            stayInSystemCheckBoxLocator = $("#wpRemember");
    public static final String validUserName = "AlexIvanov1994";
    public static final String validPassword = "38265421";

    public LoginPage setUserName(String name){
        userNameLocator.setValue(name);
        return this;
    }

    public LoginPage setPassword(String password){
        passwordLocator.setValue(password);
        return this;
    }

    public LoginPage clickSubmitButton(){
        submitButtonLocator.click();
        return this;
    }

    public LoginPage clickStayInSystemCheckBox(){
        stayInSystemCheckBoxLocator.click();
        return this;
    }
}