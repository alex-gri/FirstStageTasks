package pages.yandexdisk.menuitempages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskFolderContentPage {

    private WebDriver driver;
    private String createdFolderName;

    public YandexDiskFolderContentPage(WebDriver driver, String createdFolderName) {
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
