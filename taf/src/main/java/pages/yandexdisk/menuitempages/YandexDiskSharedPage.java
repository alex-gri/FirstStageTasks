package pages.yandexdisk.menuitempages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskSharedPage {

    private WebDriver driver;

    private By sharedContentTitle = new By.ByXPath("//h1[text()='Публичные ссылки']");

    public YandexDiskSharedPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItSharedPage() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(sharedContentTitle))
                .isDisplayed();
    }
}
