package com.epam.tat.yandex.disk.page.authorization;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

public class PassportYandexAuthorizationPage {

    private By failureMessage = By.xpath("//div[@class='passp-form-field__error']");
    private By loginTextBox = By.id("passp-field-login");
    private By passwordTextBox = By.id("passp-field-passwd");
    private By logInButton = By.xpath("//button[contains(.,'Войти')]");
    private By dialogOverlay = By.xpath("//div[@class='dialog__body']");
    private By dialogCloseButton = By.xpath("//button[@class='control button2 button2_view_default button2_size_l button2_theme_clear-inverse albums2-onboarding-base__close']");
    protected By login = By.xpath("//span[@class='user-account__name']");
    protected By userButton = By.xpath("//div[@class='user-pic user-account__pic']");
    public PassportYandexAuthorizationPage() {
    }

    public PassportYandexAuthorizationPage setLogin(String login) {
        Log.report("Typing login: " + login);
        Browser.getInstance().type(loginTextBox, login);
        return this;
    }

    public PassportYandexAuthorizationPage setPassword(String password) {
        Log.report("Typing password: " + password);
        Browser.getInstance().type(passwordTextBox, password);
        return this;
    }

    public PassportYandexAuthorizationPage logInButtonClick() {
        Log.report("Confirming input");
        Browser.getInstance().click(logInButton);
        return this;
    }

    public PassportYandexAuthorizationPage verifyOverlay() {
        Browser.getInstance().waitForPageLoadIsComplete();
        if (Browser.getInstance().isVisibleNoWait(dialogOverlay)) {
            Log.info("Overlay is present. Closing it...");
            Browser.getInstance().clickNoWait(dialogCloseButton);
        }
        return this;
    }

    public YandexDiskFilesPage openYandexDiskFilesPage() {
        return new YandexDiskFilesPage();
    }

    public boolean isLogInErrorMessagePresent() {
        Log.report("Checking if error occurred");
        return Browser.getInstance().isDisplayed(failureMessage);
    }

    public String getLoggedInAccountLogin() {
        Log.report("Checking logged in account's login");
        return Browser.getInstance().getText(login);
    }

    public PassportYandexAuthorizationPage profileAvatarButtonClick() {
        Browser.getInstance().click(userButton);
        return this;
    }
}
