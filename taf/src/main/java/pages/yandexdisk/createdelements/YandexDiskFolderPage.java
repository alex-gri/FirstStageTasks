package pages.yandexdisk.createdelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskFolderPage {

    private WebDriver driver;
    private String createdFolderName;

    public YandexDiskFolderPage(WebDriver driver, String createdFolderName) {
        this.driver = driver;
        this.createdFolderName = createdFolderName;
    }

    public boolean isFolderVisited() {
        By xpathOfCreatedFolderName = new By.ByXPath(String.format("//h1[text()='%s']", createdFolderName));
        return new WebDriverWait(driver,30)
                .until(ExpectedConditions.presenceOfElementLocated(xpathOfCreatedFolderName))
                .isDisplayed();
    }
}
