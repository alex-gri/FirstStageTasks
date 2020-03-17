package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.test.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskTextDocumentPage {

    private By iframeXpath = By.xpath("//iframe");
    private By saveStatusId = By.id("BreadcrumbSaveStatus");
    private By topRenameFieldId = By.id("BreadcrumbTitle");
    private By outlineContent = By.xpath("//*[@class='OutlineContent']");

    public YandexDiskTextDocumentPage writeToDocument(String textToWrite) {
        Log.report("Typing into document: " + textToWrite);

        /*
         * Switching driver to header's frame so we can wait page to load properly.
         * Waiting for document's save status to load before write any text.
         */
        Browser.getInstance().swtichToIframe();
        Browser.getInstance().waitForTextToBe(saveStatusId, Constants.SAVED_STATUS);
        Browser.getInstance().getWrappedDriver().switchTo().defaultContent();

        // Writing text to the document.
        Browser.getInstance().typeToBody(textToWrite);
        return this;
    }

    public YandexDiskTextDocumentPage renameDocumentFieldClick() {
        Browser.getInstance().swtichToIframe();
        Browser.getInstance().waitForTextToBe(saveStatusId, Constants.SAVED_STATUS);
        Browser.getInstance().click(topRenameFieldId);
        return this;
    }

    public YandexDiskTextDocumentPage setDocumentName(String name) {
        Log.report("Setting document name to: " + name);
        Browser.getInstance().clear(topRenameFieldId);
        Browser.getInstance().type(topRenameFieldId, name);
        Browser.getInstance().waitForTextToBe(topRenameFieldId, name);
        Browser.getInstance().getWrappedDriver().findElement(topRenameFieldId).sendKeys(Keys.ENTER);
        Browser.getInstance().waitForTextToBe(saveStatusId, Constants.SAVED_STATUS);
        Browser.getInstance().getWrappedDriver().switchTo().defaultContent();
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
        Browser.getInstance().swtichToIframe();
        StringBuilder stringBuilder = new StringBuilder();
        new WebDriverWait(Browser.getInstance().getWrappedDriver(), Constants.VISIBILITY_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(outlineContent))
                .forEach(webElement -> stringBuilder.append(Browser.getInstance().getText(webElement)));
        Browser.getInstance().getWrappedDriver().switchTo().defaultContent();
        return stringBuilder.toString().trim();
    }
}
