package pages.yandexdisk;

import customconditions.PageLoadingIsCompletedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskHomePage {

    private final String URL = "https://disk.yandex.by/";
    private WebDriver driver;

    private By logInButtonXpath = new By.ByXPath("//a[@class='button button_login header__login-link']");

    public YandexDiskHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiskHomePage openYndexDiskHomePage() {
        driver.get(URL);
        new WebDriverWait(driver, 30).until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
        return this;
    }

    public PassportYandexAuthorizationPage logInButtonClick() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(logInButtonXpath)).click();
        return new PassportYandexAuthorizationPage(driver);
    }
}
