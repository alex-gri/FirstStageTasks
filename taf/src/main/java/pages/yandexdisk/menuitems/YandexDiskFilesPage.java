package pages.yandexdisk.menuitems;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.yandexdisk.YandexDiskCreatePage;

public class YandexDiskFilesPage {

    private WebDriver driver;
    private By filesContentTitle = new By.ByXPath("//h1[text()='Файлы']");

    private By recentMenuItem = new By.ByXPath("//a[@title='Последние']");
    private By filesMenuItem = new By.ByXPath("//a[@title='Файлы']");
    private By photoMenuItem = new By.ByXPath("//a[@title='Фото']");
    private By sharedMenuItem = new By.ByXPath("//a[@title='Общий доступ']");
    private By historyMenuItem = new By.ByXPath("//a[@title='История']");
    private By archiveMenuItem = new By.ByXPath("//a[@title='Архив']");
    private By trashMenuItem = new By.ByXPath("//a[@title='Корзина']");

    private By createButton = new By.ByXPath("//button[contains(.,'Создать')]");

    public YandexDiskFilesPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiskArchivePage archiveMenuItemClick() {
        waitForElementAndClick(archiveMenuItem);
        return new YandexDiskArchivePage(driver);
    }

    public YandexDiskFilesPage filesMenuItemClickAfterClickOnAnotherItem() {

        // Changing current directory to trash first, because current could be Files already.
        waitForElementAndClick(trashMenuItem);
        waitForElementAndClick(filesMenuItem);
        return this;
    }

    public YandexDiskFilesPage filesMenuItemClick() {
        waitForElementAndClick(filesMenuItem);
        return this;
    }

    public YandexDiskHistoryPage historyMenuItemClick() {
        waitForElementAndClick(historyMenuItem);
        return new YandexDiskHistoryPage(driver);
    }

    public YandexDiskPhotoPage photoMenuItemClick() {
        waitForElementAndClick(photoMenuItem);
        return new YandexDiskPhotoPage(driver);
    }

    public YandexDiskRecentPage recentMenuItemClickAfterClickOnAnotherItem() {
        // Changing current directory to trash first, because current could be Recent already.
        waitForElementAndClick(trashMenuItem);
        waitForElementAndClick(recentMenuItem);
        return new YandexDiskRecentPage(driver);
    }

    public YandexDiskRecentPage recentMenuItemClick() {
        waitForElementAndClick(recentMenuItem);
        return new YandexDiskRecentPage(driver);
    }

    public YandexDiskSharedPage sharedMenuItemClick() {
        waitForElementAndClick(sharedMenuItem);
        return new YandexDiskSharedPage(driver);
    }

    public YandexDiskTrashPage trashMenuItemClick() {
        waitForElementAndClick(trashMenuItem);
        return new YandexDiskTrashPage(driver);
    }

    public boolean isItFilesPage() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(filesContentTitle))
                .isDisplayed();
    }

    public YandexDiskCreatePage createButtonClick() {
        waitForElementAndClick(createButton);
        return new YandexDiskCreatePage(driver);
    }

    private void waitForElementAndClick(By elementXpath) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(elementXpath))
                .click();
    }
}
