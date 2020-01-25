package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.model.Document;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskFolderPage extends AbstractMenuPage {

    private By documentXpath;
    private By openedFolderXpath = By.tagName("h1");
    private String partialDocumentXpath = "//span[text()='%s.docx']//ancestor::*[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_file js-prevent-deselect']";

    public String getOpenedFolderName() {
        return browserInstance.waitForVisibilityOfElementLocated(openedFolderXpath).getAttribute("title");
    }

    public YandexDiskTextDocumentPage openDocument(Document document) {
        documentXpath = By.xpath(String.format(partialDocumentXpath, document.getName()));
        browserInstance.doubleClick(documentXpath);
        browserInstance.swtichToTab(1);
        return document.getDocumentPage();
    }

    public YandexDiskFolderPage selectDocument(Document document) {
        documentXpath = browserInstance.xpathBuilder(partialDocumentXpath, document.getName());
        browserInstance.waitForVisibilityOfElementLocated(documentXpath).click();
        return this;
    }

    public boolean isDocumentPresent(Document document) {
        documentXpath = browserInstance.xpathBuilder(partialDocumentXpath, document.getName());
        return browserInstance.isDisplayed(documentXpath);
    }

    public boolean isDocumentInTrashOnly(Document document) {
        boolean isDocumentInSourceFolder = isDocumentPresent(document);
        if (isDocumentInSourceFolder) {
            return false;
        } else {
            trashMenuItemClick();
            return isDocumentPresent(document); // Is document in trash check.
        }
    }
}
