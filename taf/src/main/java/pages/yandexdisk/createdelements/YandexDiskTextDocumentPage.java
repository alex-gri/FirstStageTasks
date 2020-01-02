package pages.yandexdisk.createdelements;

import iohelper.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class YandexDiskTextDocumentPage {

    private WebDriver driver;

    By iframeXpath = By.xpath("//iframe");
    By saveStatusId = By.id("BreadcrumbSaveStatus");
    By topRenameFieldId = By.id("BreadcrumbTitle");
    By outlineContent = By.xpath("//span[@title='%s.docx']");

    public YandexDiskTextDocumentPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiskTextDocumentPage writeToDocument(String textToWrite) {
        switchDriverToTab(1);

        // Switching driver to header's frame so we can wait page to load properly.
        waitAndSwitchToFrame(iframeXpath);

        // Waiting for document's save status to load before write any text.
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(saveStatusId));
        driver.switchTo().defaultContent();

        // Writing text to the document.
        Actions actionsBuilder = new Actions(driver);
        actionsBuilder.sendKeys(textToWrite).perform();

        // Saving text to property-file to compare it later.
        PropertyManager.writeProperty("document.text", textToWrite);
        return this;
    }

    public YandexDiskTextDocumentPage renameDocumentFieldClick() {
        waitAndSwitchToFrame(iframeXpath);
        waitForSavedStatus();
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(topRenameFieldId))
                .click();
        driver.switchTo().defaultContent();
        return this;
    }

    public YandexDiskTextDocumentPage setDocumentName() {
        waitAndSwitchToFrame(iframeXpath);
        String documentName = String.valueOf(System.currentTimeMillis());

        Actions actionsBuilder = new Actions(driver);
        actionsBuilder
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(documentName)
                .sendKeys(Keys.ENTER).build().perform();

        waitForSavedStatus();

        PropertyManager.writeProperty("document.name", documentName);

        driver.switchTo().defaultContent();
        return this;
    }

    public YandexDiskFolderPage closeDocumentTab() {
        driver.switchTo().window(driver.getWindowHandle()).close();
        switchDriverToTab(0);
        return new YandexDiskFolderPage(driver);
    }

    public boolean isTextCorrect() {
        switchDriverToTab(1);
        List<WebElement> documentContent = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(outlineContent));
        StringBuilder stringBuilder = new StringBuilder();
        documentContent.forEach(webElement -> stringBuilder.append(webElement.getText()));
        System.out.println(PropertyManager.readProperty("document.text"));
        System.out.println(stringBuilder.toString());
        return PropertyManager.readProperty("document.text").equals(stringBuilder.toString());
    }

    private void waitForSavedStatus() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.textToBe(saveStatusId, "Сохранено в Yandex"));
    }

    private void waitAndSwitchToFrame(By frameXpath) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameXpath));
    }

    private void switchDriverToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabIndex));
    }
}
