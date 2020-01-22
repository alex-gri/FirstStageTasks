package com.epam.tat.yandex.disk.page.home;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import org.openqa.selenium.By;

public class YandexDiskHomePage {

    private static final String URL = "https://disk.yandex.by/";
    private static Browser browserInstance;

    private By logInButtonXpath = By.xpath("//a[@class='button button_login header__login-link']");

    public YandexDiskHomePage() {
        this.browserInstance = Browser.getInstance();
    }

    public YandexDiskHomePage openYandexDiskHomePage() {
        browserInstance.get(URL);
        return this;
    }

    public PassportYandexAuthorizationPage logInButtonClick() {
        browserInstance.click(logInButtonXpath);
        return new PassportYandexAuthorizationPage();
    }
}
