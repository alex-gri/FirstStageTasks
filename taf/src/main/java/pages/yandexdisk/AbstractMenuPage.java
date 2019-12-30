package pages.yandexdisk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.yandexdisk.menuitems.*;

public abstract class AbstractMenuPage {

    protected WebDriver driver;

    protected By recentMenuItem = new By.ByXPath("//a[@title='Последние']");
    protected By filesMenuItem = new By.ByXPath("//a[@title='Файлы']");
    protected By photoMenuItem = new By.ByXPath("//a[@title='Фото']");
    protected By sharedMenuItem = new By.ByXPath("//a[@title='Общий доступ']");
    protected By historyMenuItem = new By.ByXPath("//a[@title='История']");
    protected By archiveMenuItem = new By.ByXPath("//a[@title='Архив']");
    protected By trashMenuItem = new By.ByXPath("//a[@title='Корзина']");

    protected By createButton = new By.ByXPath("//button[contains(.,'Создать')]");

    public AbstractMenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiskCreatePage createButtonClick() {
        waitForElementAndClick(createButton);
        return new YandexDiskCreatePage(driver);
    }

    public YandexDiskArchivePage archiveMenuItemClick() {
        waitForElementAndClick(archiveMenuItem);
        return new YandexDiskArchivePage(driver);
    }

    public YandexDiskFilesPage filesMenuItemClickAfterClickOnAnotherItem() {

        // Changing current directory to trash first, because current could be Files already.
        waitForElementAndClick(trashMenuItem);
        waitForElementAndClick(filesMenuItem);
        return new YandexDiskFilesPage(driver);
    }

    public YandexDiskFilesPage filesMenuItemClick() {
        waitForElementAndClick(filesMenuItem);
        return new YandexDiskFilesPage(driver);
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

    public void waitForElementAndClick(By elementXpath) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(elementXpath))
                .click();
    }
}
