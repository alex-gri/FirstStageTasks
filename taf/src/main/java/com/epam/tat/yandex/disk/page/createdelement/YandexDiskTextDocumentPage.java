package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.framework.util.DataStorage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class YandexDiskTextDocumentPage {

    protected static Browser browserInstance;
    private Random random = new Random();

    By iframeXpath = By.xpath("//iframe");
    By saveStatusId = By.xpath("//form[@id='form1']//div[@id='AppHeaderPanel']//span[@id='BreadcrumbSaveStatus']");
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

        // Saving text to property-file to compare it later.
        DataStorage.addObject("document.text", textToWrite);
        return this;
    }

    public YandexDiskTextDocumentPage renameDocumentFieldClick() {
        browserInstance.swtichToFrame(iframeXpath);
        browserInstance.waitForTextToBe(saveStatusId, "Сохранено в Yandex");
        browserInstance.click(topRenameFieldId);
        browserInstance.swtichToFrame(null);
        return this;
    }

    public YandexDiskTextDocumentPage setDocumentName() {
        browserInstance.swtichToFrame(iframeXpath);
        String documentName = String.valueOf(Math.abs(random.nextInt()));

        browserInstance.clear(topRenameFieldId);
        browserInstance.type(topRenameFieldId, documentName);
        browserInstance.getWrappedDriver().findElement(topRenameFieldId).sendKeys(Keys.ENTER);
        browserInstance.waitForTextToBe(saveStatusId, "Сохранено в Yandex");

        DataStorage.addObject("document.name", documentName);

        browserInstance.swtichToFrame(null);
        return this;
    }

    public YandexDiskFolderPage closeDocumentTab() {
        browserInstance.closeCurrentTab();
        browserInstance.swtichToTab(0);
        return new YandexDiskFolderPage();
    }

    public boolean isTextCorrect() {
        browserInstance.swtichToFrame(iframeXpath);
        StringBuilder stringBuilder = new StringBuilder();
        new WebDriverWait(browserInstance.getWrappedDriver(), 20)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(outlineContent))
                .forEach(webElement -> stringBuilder.append(browserInstance.getText(webElement)));
        browserInstance.swtichToFrame(null);
        return ((String) DataStorage.getObjectByKey("document.text")).trim().equals(stringBuilder.toString().trim());
    }
}
