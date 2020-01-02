package pages.yandexdisk.createdelements;

import iohelper.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.yandexdisk.AbstractMenuPage;

public class YandexDiskFolderPage extends AbstractMenuPage {

    private String createdFolderName;
    private String partialFolderNameXpath = "//h1[text()='%s']";
    private String listingItemXpath = "//span[@class='clamped-text' and contains(.,'%s')]//ancestor::div[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_file js-prevent-deselect']";

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
        Actions actionsBuilder = new Actions(driver);
        actionsBuilder.doubleClick(document);
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
        return new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(nameToCheckXpath))
                .isDisplayed();
    }
}
