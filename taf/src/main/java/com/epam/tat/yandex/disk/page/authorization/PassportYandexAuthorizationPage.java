package com.epam.tat.yandex.disk.page.authorization;

import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

public class PassportYandexAuthorizationPage {

    private static Browser browserInstance;

    private By loginTextBox = By.id("passp-field-login");
    private By passwordTextBox = By.id("passp-field-passwd");
    private By logInButton = By.xpath("//button[contains(.,'Войти')]");
    private By successMessage = By.xpath("//span[@title='Загрузить файлы']");
    private By failureMessage = By.xpath("//div[@class='passp-form-field__error']");

    public PassportYandexAuthorizationPage() {
        this.browserInstance = Browser.getInstance();
    }

    public PassportYandexAuthorizationPage setLogin(String login) {
        browserInstance.type(loginTextBox, login);
        return this;
    }

    public PassportYandexAuthorizationPage setPassword(String password) {
        browserInstance.type(passwordTextBox, password);
        return this;
    }

    public PassportYandexAuthorizationPage logInButtonClick() {
        browserInstance.click(logInButton);
        return this;
    }

    public YandexDiskFilesPage openYandexDiskFilesPage() {
        return new YandexDiskFilesPage();
    }

    public boolean isLogInSuccessful() {
        return browserInstance.isDisplayed(successMessage);
    }

    public boolean isLogInFailed() {
        return browserInstance.isDisplayed(failureMessage);
    }
}
