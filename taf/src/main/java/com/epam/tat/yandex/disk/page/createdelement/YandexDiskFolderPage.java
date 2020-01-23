package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.yandex.disk.page.service.DocumentService;
import com.epam.tat.yandex.disk.page.service.FolderService;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskFolderPage extends AbstractMenuPage {

    private By documentXpath;
    private String partialFolderNameXpath = "//h1[text()='%s']";
    private String listingItemXpath = "//span[@title='%s.docx']//ancestor::*[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_file js-prevent-deselect']";

    public boolean isFolderVisited() {
        By createdFolderNameXpath = By.xpath(String.format(partialFolderNameXpath, FolderService.getFolderName()));
        return browserInstance.isDisplayed(createdFolderNameXpath);
    }

    public YandexDiskTextDocumentPage openDocument() {
        documentXpath = By.xpath(String.format(listingItemXpath, DocumentService.getDocumentName()));
        browserInstance.doubleClick(documentXpath);
        browserInstance.swtichToTab(1);
        return new YandexDiskTextDocumentPage();
    }

    public boolean isDocumentInAppropriateFolder() {
        boolean isFolderAppropriate = isCorrectNameDisplayed(partialFolderNameXpath, FolderService.getFolderName());
        boolean isDocumentInThisFolder = isCorrectNameDisplayed(listingItemXpath, DocumentService.getDocumentName());
        return isFolderAppropriate && isDocumentInThisFolder;
    }

    private boolean isCorrectNameDisplayed(String partialNameXpath, String nameToCheck) {
        By nameToCheckXpath = By.xpath(String.format(partialNameXpath, nameToCheck));
        return browserInstance.isDisplayed(nameToCheckXpath);
    }

    public YandexDiskFolderPage selectDocument() {
        documentXpath = By.xpath(String.format(listingItemXpath, DocumentService.getDocumentName()));
        browserInstance.waitForVisibilityOfElementLocated(documentXpath).click();
        return this;
    }

    public boolean isDocumentInTrashOnly() {
        String documentName = DocumentService.getDocumentName();
        boolean isDocumentInSourceFolder = isCorrectNameDisplayed(listingItemXpath, documentName);
        if (isDocumentInSourceFolder) {
            return false;
        } else {
            trashMenuItemClick();
            return isCorrectNameDisplayed(listingItemXpath, documentName); // Is document in trash check.
        }
    }
}
