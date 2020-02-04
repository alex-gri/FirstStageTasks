package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskTextDocumentPage {

    By iframeXpath = By.xpath("//iframe");
    By saveStatusId = By.id("BreadcrumbSaveStatus");
    By topRenameFieldId = By.id("BreadcrumbTitle");
    By outlineContent = By.xpath("//*[@class='OutlineContent']");

    public YandexDiskTextDocumentPage() {
    }

    public YandexDiskTextDocumentPage writeToDocument(String textToWrite) {
        Log.report("Typing into document: " + textToWrite);

        /*
         * Switching driver to header's frame so we can wait page to load properly.
         * Waiting for document's save status to load before write any text.
         */
        Browser.getInstance().swtichToFrame(iframeXpath);
        Browser.getInstance().waitForTextToBe(saveStatusId, "Сохранено в Yandex");
        Browser.getInstance().swtichToFrame(null);

        // Writing text to the document.
        Browser.getInstance().typeToBody(textToWrite);
        return this;
    }

    public YandexDiskTextDocumentPage renameDocumentFieldClick() {
        Browser.getInstance().swtichToFrame(iframeXpath);
        Browser.getInstance().waitForTextToBe(saveStatusId, "Сохранено в Yandex");
        Browser.getInstance().click(topRenameFieldId);
        Browser.getInstance().swtichToFrame(null);
        return this;
    }

    public YandexDiskTextDocumentPage setDocumentName(String name) {
        Log.report("Setting document name to: " + name);
        Browser.getInstance().swtichToFrame(iframeXpath);
        Browser.getInstance().clear(topRenameFieldId);
        Browser.getInstance().type(topRenameFieldId, name);
        Browser.getInstance().waitForTextToBe(topRenameFieldId, name);
        Browser.getInstance().getWrappedDriver().findElement(topRenameFieldId).sendKeys(Keys.ENTER);
        Browser.getInstance().waitForTextToBe(saveStatusId, "Сохранено в Yandex");
        Browser.getInstance().swtichToFrame(null);
        return this;
    }

    public YandexDiskFolderPage closeDocumentTab() {
        Log.report("Closing document");
        Browser.getInstance().closeCurrentTab();
        Browser.getInstance().swtichToTab(0);
        return new YandexDiskFolderPage();
    }

    public String getDocumentText() {
        Log.report("Getting text from document");
        Browser.getInstance().swtichToFrame(iframeXpath);
        StringBuilder stringBuilder = new StringBuilder();
        new WebDriverWait(Browser.getInstance().getWrappedDriver(), 20)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(outlineContent))
                .forEach(webElement -> stringBuilder.append(Browser.getInstance().getText(webElement)));
        Browser.getInstance().swtichToFrame(null);
        return stringBuilder.toString().trim();
    }
}
