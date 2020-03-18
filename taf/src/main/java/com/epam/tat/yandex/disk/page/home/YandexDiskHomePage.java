package com.epam.tat.yandex.disk.page.home;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import org.openqa.selenium.By;

public class YandexDiskHomePage {

    private static final String URL = "https://disk.yandex.by/";

    private By logInButtonXpath = By.xpath("//a[@class='button button_login header__login-link']");

    public YandexDiskHomePage openYandexDiskHomePage() {
        Browser.getInstance().get(URL);
        return this;
    }

    public PassportYandexAuthorizationPage logInButtonClick() {
        Browser.getInstance().click(logInButtonXpath);
        return new PassportYandexAuthorizationPage();
    }
}
