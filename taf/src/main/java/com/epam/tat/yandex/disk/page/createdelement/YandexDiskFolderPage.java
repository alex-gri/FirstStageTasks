package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.model.Document;
import com.epam.tat.framework.model.Folder;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskFolderPage extends AbstractMenuPage {

    private By documentXpath;
    private String partialFolderXpath = "//h1[text()='%s']";
    private String partialDocumentXpath = "//span[text()='%s.docx']//ancestor::*[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_file js-prevent-deselect']";

    public boolean isFolderVisited(Folder folder) {
        By createdFolderNameXpath = By.xpath(String.format(partialFolderXpath, folder.getName()));
        return browserInstance.isDisplayed(createdFolderNameXpath);
    }

    public YandexDiskTextDocumentPage openDocument(Document document) {
        documentXpath = By.xpath(String.format(partialDocumentXpath, document.getName()));
        browserInstance.doubleClick(documentXpath);
        browserInstance.swtichToTab(1);
        return new YandexDiskTextDocumentPage();
    }

    public boolean isDocumentInAppropriateFolder(Document document, Folder folder) {
        boolean isFolderAppropriate = browserInstance.isCorrectNameDisplayed(partialFolderXpath, folder.getName());
        boolean isDocumentInThisFolder = browserInstance.isCorrectNameDisplayed(partialDocumentXpath, document.getName());
        return isFolderAppropriate && isDocumentInThisFolder;
    }

//    private boolean isCorrectNameDisplayed(String partialNameXpath, String nameToCheck) {
//        By nameToCheckXpath = By.xpath(String.format(partialNameXpath, nameToCheck));
//        return browserInstance.isDisplayed(nameToCheckXpath);
//    }

    public YandexDiskFolderPage selectDocument(Document document) {
        documentXpath = By.xpath(String.format(partialDocumentXpath, document.getName()));
        browserInstance.waitForVisibilityOfElementLocated(documentXpath).click();
        return this;
    }

    public boolean isDocumentInTrashOnly(Document document) {
        boolean isDocumentInSourceFolder = browserInstance.isCorrectNameDisplayed(partialDocumentXpath, document.getName());
        if (isDocumentInSourceFolder) {
            return false;
        } else {
            trashMenuItemClick();
            return browserInstance.isCorrectNameDisplayed(partialDocumentXpath, document.getName()); // Is document in trash check.
        }
    }
}
