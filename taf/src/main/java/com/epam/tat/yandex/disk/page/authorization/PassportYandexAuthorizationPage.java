package com.epam.tat.yandex.disk.page.authorization;

import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

public class PassportYandexAuthorizationPage {

    private static Browser browserInstance;

    private By failureMessage = By.xpath("//div[@class='passp-form-field__error']");
    private By loginTextBox = By.id("passp-field-login");
    private By passwordTextBox = By.id("passp-field-passwd");
    private By logInButton = By.xpath("//button[contains(.,'Войти')]");
    protected By login = By.xpath("//span[@class='user-account__name']");
    protected By userButton = By.xpath("//div[@class='user-pic user-account__pic']");


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

    public boolean isLogInErrorMessagePresent() {
        return browserInstance.isDisplayed(failureMessage);
    }

    public String getLoggedInAccountLogin() {
        browserInstance.click(userButton);
        return browserInstance.getText(browserInstance.waitForVisibilityOfElementLocated(login));
    }
}
