package pages.yandexdisk.createdelements;

import iohelper.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.yandexdisk.AbstractMenuPage;

import java.time.Duration;

public class YandexDiskFolderPage extends AbstractMenuPage {

    private String createdFolderName;
    private String partialFolderNameXpath = "//h1[text()='%s']";
    private String listingItemXpath = "//span[@title='%s.docx']//ancestor::*[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_file js-prevent-deselect']";
    private By deleteButton = By.xpath("//span[text()='Удалить']//ancestor::button");

    public YandexDiskFolderPage(WebDriver driver, String createdFolderName) {
        super(driver);
        this.createdFolderName = createdFolderName;
    }

    public YandexDiskFolderPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFolderVisited() {
        By createdFolderNameXpath = By.xpath(String.format(partialFolderNameXpath, createdFolderName));
        return new WebDriverWait(driver,30)
                .until(ExpectedConditions.presenceOfElementLocated(createdFolderNameXpath))
                .isDisplayed();
    }

    public YandexDiskTextDocumentPage openDocument() {
        By documentXpath = By.xpath(String.format(listingItemXpath, PropertyManager.readProperty("document.name")));
        WebElement document = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(documentXpath));
        new Actions(driver).doubleClick(document).perform();
        switchDriverToTab(1);
        return new YandexDiskTextDocumentPage(driver);
    }

    public boolean isDocumentInAppropriateFolder() {
        boolean isFolderAppropriate = isCorrectNameDisplayed(partialFolderNameXpath,
                                                             PropertyManager.readProperty("folder.name"));
        boolean isDocumentInThisFolder = isCorrectNameDisplayed(listingItemXpath,
                                                                PropertyManager.readProperty("document.name"));
        return isFolderAppropriate && isDocumentInThisFolder;
    }

    private boolean isCorrectNameDisplayed(String partialNameXpath, String nameToCheck) {
        By nameToCheckXpath = By.xpath(String.format(partialNameXpath, nameToCheck));
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(TimeoutException.class)
                .until(ExpectedConditions.presenceOfElementLocated(nameToCheckXpath))
                .isDisplayed();
    }

    public YandexDiskFolderPage selectDocument() {
        By documentXpath = By.xpath(String.format(listingItemXpath, PropertyManager.readProperty("document.name")));
        WebElement document = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(documentXpath));
        new Actions(driver).click(document).perform();
        return this;
    }

    public YandexDiskFolderPage deleteButtonClick() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementAndClick(deleteButton);
        return this;
    }

    public boolean isDocumentInSourceFolderAndInTrash() {
        boolean isDocumentInSourceFolder = isCorrectNameDisplayed(listingItemXpath, PropertyManager.readProperty("document.name"));
        trashMenuItemClick();
        boolean isDocumentInTrash = isCorrectNameDisplayed(listingItemXpath, PropertyManager.readProperty("document.name"));
        return isDocumentInSourceFolder && isDocumentInTrash;
    }
}
