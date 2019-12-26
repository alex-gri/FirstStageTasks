package pages.yandexdisk.menuitempages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskHistoryPage {

    private WebDriver driver;

    private By historyContentTitle = new By.ByXPath("//h1[text()='История']");

    public YandexDiskHistoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItHistoryPage() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(historyContentTitle))
                .isDisplayed();
    }
}
