package com.epam.tat.yandex.disk.page.createdelement;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.framework.util.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
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
        browserInstance.swtichToTab(1);

        /*
         * Switching driver to header's frame so we can wait page to load properly.
         * Waiting for document's save status to load before write any text.
         */
        browserInstance.swtichToFrame(iframeXpath);
        waitForSavedStatus();
        browserInstance.swtichToFrame(null);

        // Writing text to the document.
        Actions actionsBuilder = new Actions(browserInstance.getWrappedDriver());
        actionsBuilder.sendKeys(textToWrite).perform();

        // Saving text to property-file to compare it later.
        PropertyManager.writeProperty("document.text", textToWrite);
        return this;
    }

    public YandexDiskTextDocumentPage renameDocumentFieldClick() {
        browserInstance.swtichToFrame(iframeXpath);
        waitForSavedStatus();
        browserInstance.click(topRenameFieldId);
        browserInstance.swtichToFrame(null);
        return this;
    }

    public YandexDiskTextDocumentPage setDocumentName() {
        browserInstance.swtichToFrame(iframeXpath);
        String documentName = String.valueOf(Math.abs(random.nextInt()));

        browserInstance.clear(topRenameFieldId);
        browserInstance.type(topRenameFieldId, documentName);
        waitForSavedStatus();

        PropertyManager.writeProperty("document.name", documentName);

        browserInstance.swtichToFrame(null);
        return this;
    }

    public YandexDiskFolderPage closeDocumentTab() {
        browserInstance.closeCurrentTab();
        browserInstance.swtichToTab(1);
        return new YandexDiskFolderPage();
    }

    public boolean isTextCorrect() {
        browserInstance.swtichToFrame(iframeXpath);
        StringBuilder stringBuilder = new StringBuilder();
        new WebDriverWait(browserInstance.getWrappedDriver(), 20)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(outlineContent))
                .forEach(webElement -> stringBuilder.append(webElement.getText()));
        browserInstance.swtichToFrame(null);
        return PropertyManager.readProperty("document.text").trim().equals(stringBuilder.toString().trim());
    }

    private void waitForSavedStatus() {
        new WebDriverWait(browserInstance.getWrappedDriver(), 30)
                .until(ExpectedConditions.textToBe(saveStatusId, "Сохранено в Yandex"));
    }
}
