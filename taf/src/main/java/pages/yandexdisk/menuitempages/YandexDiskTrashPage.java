package pages.yandexdisk.menuitempages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskTrashPage {

    private WebDriver driver;

    private By trashContentTitle = new By.ByXPath("//h1[text()='Корзина']");

    public YandexDiskTrashPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItTrashPage() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(trashContentTitle))
                .isDisplayed();
    }
}
