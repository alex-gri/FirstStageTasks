package pages.yandexdisk;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassportYandexAuthorizationPage {

    private WebDriver driver;

    private By loginTextBox = new By.ById("passp-field-login");
    private By passwordTextBox = new By.ById("passp-field-passwd");
    private By logInButton = new By.ByXPath("//button[contains(.,'Войти')]");
    private By successMessage = new By.ByXPath("//h1[contains(.,'Файлы')]");
    private By failureMessage = new By.ByXPath("//div[@class='passp-form-field__error']");

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

    public boolean isLogInSuccessful() {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(successMessage))
                    .isDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isLogInFailed() {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(failureMessage))
                    .isDisplayed();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
