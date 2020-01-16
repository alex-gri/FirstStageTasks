package com.epam.tat.yandex.disk.page.home;

import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskHomePage {

    private static final String URL = "https://disk.yandex.by/";
    private WebDriver driver;

    private By logInButtonXpath = By.xpath("//a[@class='button button_login header__login-link']");

    public YandexDiskHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiskHomePage openYandexDiskHomePage() {
        driver.get(URL);
        return this;
    }

    public PassportYandexAuthorizationPage logInButtonClick() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(logInButtonXpath)).click();
        return new PassportYandexAuthorizationPage(driver);
    }
}
