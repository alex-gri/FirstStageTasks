package pages.yandexdisk.menuitems;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskArchivePage {

    private WebDriver driver;

    private By archiveContentTitle = new By.ByXPath("//h1[text()='Архив']");

    public YandexDiskArchivePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItArchivePage() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(archiveContentTitle))
                .isDisplayed();
    }
}
