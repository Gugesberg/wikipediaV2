package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement loginLinkLocator = $("#pt-login"),
            depositeLinkLocator = $("#pt-anoncontribs"),
            savePageAsPDFLinkLocator = $("#coll-download-as-rl"),
            searchInputLocator = $("#searchInput");

    public MainPage pushLoginLink(){
        loginLinkLocator.click();
        return this;
    }
    public MainPage pushDepositeLink(){
        depositeLinkLocator.click();
        return this;
    }
    public MainPage pushSavePageAsPDFLink(){
        savePageAsPDFLinkLocator.click();
        return this;
    }
    public MainPage getSearch(String value){
        searchInputLocator.setValue(value).pressEnter();
        return this;
    }

}