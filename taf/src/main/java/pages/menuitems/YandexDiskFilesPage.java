package pages.menuitems;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractMenuPage;

public class YandexDiskFilesPage extends AbstractMenuPage {

    private By filesContentTitle = By.xpath("//h1[text()='Файлы']");

    public YandexDiskFilesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItFilesPage() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(filesContentTitle))
                .isDisplayed();
    }
}
