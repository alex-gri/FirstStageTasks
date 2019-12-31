package pages.yandexdisk.createdelements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class YandexDiskTextDocumentPage {

    private WebDriver driver;
    private String documentName;

    By iframeXpath = new By.ByXPath("//iframe");
    By saveStatusId = new By.ById("BreadcrumbSaveStatus");
    By easyRenameFieldId = new By.ById("BreadcrumbTitle");

    public YandexDiskTextDocumentPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiskTextDocumentPage writeToDocument(String textToWrite) {
        switchDriverToDocumentTab();

        // Switching driver to header's frame so we can wait page to load properly.
        waitAndSwitchToFrameByXpath(iframeXpath);

        // Waiting for document's save status to load before write any text.
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(saveStatusId));
        driver.switchTo().defaultContent();

        // Writing text to the document.
        Actions actionsBuilder = new Actions(driver);
        actionsBuilder.sendKeys(textToWrite).perform();

        writeProperty("document.text", textToWrite);
        return this;
    }

    public YandexDiskTextDocumentPage renameDocumentFieldClick() {
        waitAndSwitchToFrameByXpath(iframeXpath);
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.textToBe(saveStatusId, "Сохранено в Yandex"));
        waitForElementAndClick(easyRenameFieldId);
        driver.switchTo().defaultContent();
        return this;
    }

    public YandexDiskTextDocumentPage setDocumentName() {
        waitAndSwitchToFrameByXpath(iframeXpath);
        documentName = String.valueOf(System.currentTimeMillis());

        Actions actionsBuilder = new Actions(driver);
        actionsBuilder
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(documentName)
                .sendKeys(Keys.ENTER).build().perform();

        writeProperty("document.name", documentName);

        driver.switchTo().defaultContent();
        return this;
    }

    private void waitAndSwitchToFrameByXpath(By frameXpath) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameXpath));
    }

    private void writeProperty(String key, String value) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        String propertyFilePath = "src/main/resources/config.properties";
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(propertyFilePath);
            properties.load(fileInputStream);
            fileInputStream.close();

            properties.setProperty(key, value);

            fileOutputStream = new FileOutputStream(propertyFilePath);
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException e) {
            System.err.println("Error: Property file is not found!");
        }
    }

    private void switchDriverToDocumentTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    private void waitForElementAndClick(By elementXpath) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(elementXpath))
                .click();
    }
}
