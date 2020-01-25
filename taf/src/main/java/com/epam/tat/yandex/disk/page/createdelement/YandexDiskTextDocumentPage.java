package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskTextDocumentPage {

    protected static Browser browserInstance;

    By iframeXpath = By.xpath("//iframe");
    By saveStatusId = By.id("BreadcrumbSaveStatus");
    By topRenameFieldId = By.id("BreadcrumbTitle");
    By outlineContent = By.xpath("//*[@class='OutlineContent']");

    public YandexDiskTextDocumentPage() {
        this.browserInstance = Browser.getInstance();
    }

    public YandexDiskTextDocumentPage writeToDocument(String textToWrite) {

        /*
         * Switching driver to header's frame so we can wait page to load properly.
         * Waiting for document's save status to load before write any text.
         */
        browserInstance.swtichToFrame(iframeXpath);
        browserInstance.waitForTextToBe(saveStatusId, "Сохранено в Yandex");
        browserInstance.swtichToFrame(null);

        // Writing text to the document.
        browserInstance.typeToBody(textToWrite);
        return this;
    }

    public YandexDiskTextDocumentPage renameDocumentFieldClick() {
        browserInstance.swtichToFrame(iframeXpath);
        browserInstance.waitForTextToBe(saveStatusId, "Сохранено в Yandex");
        browserInstance.click(topRenameFieldId);
        browserInstance.swtichToFrame(null);
        return this;
    }

    public YandexDiskTextDocumentPage setDocumentName(String name) {
        browserInstance.swtichToFrame(iframeXpath);
        browserInstance.clear(topRenameFieldId);
        browserInstance.type(topRenameFieldId, name);
        browserInstance.waitForTextToBe(topRenameFieldId, name);
        browserInstance.getWrappedDriver().findElement(topRenameFieldId).sendKeys(Keys.ENTER);
        browserInstance.waitForTextToBe(saveStatusId, "Сохранено в Yandex");
        browserInstance.swtichToFrame(null);
        return this;
    }

    public YandexDiskFolderPage closeDocumentTab() {
        browserInstance.closeCurrentTab();
        browserInstance.swtichToTab(0);
        return new YandexDiskFolderPage();
    }

    public String getDocumentText() {
        browserInstance.swtichToFrame(iframeXpath);
        StringBuilder stringBuilder = new StringBuilder();
        new WebDriverWait(browserInstance.getWrappedDriver(), 20)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(outlineContent))
                .forEach(webElement -> stringBuilder.append(browserInstance.getText(webElement)));
        browserInstance.swtichToFrame(null);
        return stringBuilder.toString().trim();
    }
}
