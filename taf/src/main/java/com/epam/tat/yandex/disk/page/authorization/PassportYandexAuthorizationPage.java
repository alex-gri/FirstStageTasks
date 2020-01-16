package com.epam.tat.yandex.disk.page.authorization;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

public class PassportYandexAuthorizationPage {

    private WebDriver driver;

    private By loginTextBox = By.id("passp-field-login");
    private By passwordTextBox = By.id("passp-field-passwd");
    private By logInButton = By.xpath("//button[contains(.,'Войти')]");
    private By successMessage = By.xpath("//span[@title='Загрузить файлы']");
    private By failureMessage = By.xpath("//div[@class='passp-form-field__error']");

    public PassportYandexAuthorizationPage(WebDriver driver) {
        this.driver = driver;
    }

    public PassportYandexAuthorizationPage setLogin(String login) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(loginTextBox))
                                                      .sendKeys(login);
        return this;
    }

    public PassportYandexAuthorizationPage setPassword(String password) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(passwordTextBox))
                                                      .sendKeys(password);
        return this;
    }

    public PassportYandexAuthorizationPage logInButtonClick() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(logInButton))
                                                      .click();
        return this;
    }

    public YandexDiskFilesPage openYandexDiskFilesPage() {
        return new YandexDiskFilesPage(driver);
    }

    public boolean isLogInSuccessful() {
        try {
            return new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(successMessage))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isLogInFailed() {
        try {
            return new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(failureMessage))
                    .isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
