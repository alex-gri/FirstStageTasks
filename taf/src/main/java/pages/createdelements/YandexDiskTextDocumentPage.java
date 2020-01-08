package pages.createdelements;

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
import java.util.Random;

public class YandexDiskTextDocumentPage {

    private WebDriver driver;
    private Random random = new Random();

    By iframeXpath = By.xpath("//iframe");
    By saveStatusId = By.xpath("//form[@id='form1']//div[@id='AppHeaderPanel']//span[@id='BreadcrumbSaveStatus']");
    By topRenameFieldId = By.id("BreadcrumbTitle");
    By outlineContent = By.xpath("//*[@class='OutlineContent']");

    public YandexDiskTextDocumentPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiskTextDocumentPage writeToDocument(String textToWrite) {
        switchDriverToTab(1);

        /*
         * Switching driver to header's frame so we can wait page to load properly.
         * Waiting for document's save status to load before write any text.
         */
        waitAndSwitchToFrame(iframeXpath);
        waitForSavedStatus();
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
        String documentName = String.valueOf(Math.abs(random.nextInt()));

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
        waitAndSwitchToFrame(iframeXpath);
        StringBuilder stringBuilder = new StringBuilder();
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(outlineContent))
                .forEach(webElement -> stringBuilder.append(webElement.getText()));
        driver.switchTo().defaultContent();
        return PropertyManager.readProperty("document.text").trim().equals(stringBuilder.toString().trim());
    }

    private void waitForSavedStatus() {
        new WebDriverWait(driver, 50)
                .until(ExpectedConditions.textToBe(saveStatusId, "Сохранено в Yandex"));
    }

    private void waitAndSwitchToFrame(By frameXpath) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameXpath));
    }

    // Not using inheritance since it's logically different page from AbstractMenuPage.
    private void switchDriverToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(tabIndex));
    }
}
