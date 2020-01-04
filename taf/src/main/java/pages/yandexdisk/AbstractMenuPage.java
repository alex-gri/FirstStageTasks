package pages.yandexdisk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.yandexdisk.menuitems.*;

import java.util.ArrayList;

public abstract class AbstractMenuPage {

    protected WebDriver driver;

    protected By recentMenuItem = By.xpath("//a[@title='Последние']");
    protected By filesMenuItem = By.xpath("//a[@title='Файлы']");
    protected By photoMenuItem = By.xpath("//a[@title='Фото']");
    protected By sharedMenuItem = By.xpath("//a[@title='Общий доступ']");
    protected By historyMenuItem = By.xpath("//a[@title='История']");
    protected By archiveMenuItem = By.xpath("//a[@title='Архив']");
    protected By trashMenuItem = By.xpath("//a[@title='Корзина']");

    protected By createButton = By.xpath("//button[contains(.,'Создать')]");

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

    public void switchDriverToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        new WebDriverWait(driver, 20).until(WebDriver::switchTo).window(tabs.get(1));
    }

    public void waitForElementAndClick(By elementXpath) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.elementToBeClickable(elementXpath))
                .click();
    }
}
