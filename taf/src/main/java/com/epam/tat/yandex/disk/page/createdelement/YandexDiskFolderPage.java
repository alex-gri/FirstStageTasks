package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.model.Document;
import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskFolderPage extends AbstractMenuPage {

    private By documentXpath;
    private String partialDocumentXpath = "//span[text()='%s.docx']//ancestor::*[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_file js-prevent-deselect']";

    public YandexDiskTextDocumentPage openDocument(Document document) {
        Log.report("Opening document: " + document.getName());
        documentXpath = By.xpath(String.format(partialDocumentXpath, document.getName()));
        Browser.getInstance().doubleClick(documentXpath);
        Browser.getInstance().swtichToTab(1);
        return document.getDocumentPage();
    }

    public YandexDiskFolderPage selectDocument(Document document) {
        Log.report("Selecting document: " + document.getName());
        documentXpath = Browser.getInstance().xpathBuilder(partialDocumentXpath, document.getName());
        Browser.getInstance().waitForVisibilityOfElementLocated(documentXpath).click();
        return this;
    }

    public boolean isDocumentPresent(Document document) {
        Log.report("Looking for document named:" + document.getName());
        documentXpath = Browser.getInstance().xpathBuilder(partialDocumentXpath, document.getName());
        return Browser.getInstance().isDisplayed(documentXpath);
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
