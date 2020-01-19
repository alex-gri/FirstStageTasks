package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.util.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskFolderPage extends AbstractMenuPage {

    private String createdFolderName;
    private String partialFolderNameXpath = "//h1[text()='%s']";
    private String listingItemXpath = "//span[@title='%s.docx']//ancestor::*[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_file js-prevent-deselect']";

    public YandexDiskFolderPage(String createdFolderName) {
        this.createdFolderName = createdFolderName;
    }

    public YandexDiskFolderPage() {
    }

    public boolean isFolderVisited() {
        By createdFolderNameXpath = By.xpath(String.format(partialFolderNameXpath, createdFolderName));
        return browserInstance.isDisplayed(createdFolderNameXpath);
    }

    public YandexDiskTextDocumentPage openDocument() {
        By documentXpath = By.xpath(String.format(listingItemXpath, PropertyManager.readProperty("document.name")));
        WebElement document = browserInstance.waitForVisibilityOfElement(documentXpath);
        new Actions(browserInstance.getWrappedDriver()).doubleClick(document).perform();
        browserInstance.swtichToTab(1);
        return new YandexDiskTextDocumentPage();
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
        return browserInstance.isDisplayed(nameToCheckXpath);
    }

    public YandexDiskFolderPage selectDocument() {
        By documentXpath = By.xpath(String.format(listingItemXpath, PropertyManager.readProperty("document.name")));
        browserInstance.waitForVisibilityOfElement(documentXpath).click();
        return this;
    }

    public boolean isDocumentInTrashOnly() {
        boolean isDocumentInSourceFolder = isCorrectNameDisplayed(listingItemXpath, PropertyManager.readProperty("document.name"));
        trashMenuItemClick();
        boolean isDocumentInTrash = isCorrectNameDisplayed(listingItemXpath, PropertyManager.readProperty("document.name"));
        return !isDocumentInSourceFolder && isDocumentInTrash;
    }
}
