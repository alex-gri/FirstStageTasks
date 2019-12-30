package pages.yandexdisk.createdelements;

import customconditions.TextSelectedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class YandexDiskTextDocumentPage {

    private WebDriver driver;
    private String documentName;
    private WebElement iframe;

    By iframeXpath = new By.ByXPath("//iframe");
    By fileMenuButtonXpath = new By.ByXPath("//button[@data-unique-id='FileMenu']");
    By renameMenuOptionId = new By.ById("jbtnRenameDialog-Menu48");
    By documentNameFieldId = new By.ById("txtDocumentName");
    By okButtonId = new By.ById("WACDialogActionButton");
    By saveStatusId = new By.ById("BreadcrumbSaveStatus");
    By cheatRename = new By.ById("BreadcrumbTitle");

    public YandexDiskTextDocumentPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexDiskTextDocumentPage writeToDocument(String textToWrite) {

        switchDriverToDocumentTab();

        // Switching driver to header's frame so we can wait page to load properly.
        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeXpath));

        // Waiting for document to be saved status before write any text.
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(saveStatusId));
        driver.switchTo().defaultContent();

        // Writing text to the document.
        Actions builder = new Actions(driver);
        builder.sendKeys(textToWrite).perform();
        return this;
    }

    public YandexDiskTextDocumentPage fileMenuButtonClick() {
//        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeXpath));
//        new WebDriverWait(driver, 40).until(ExpectedConditions.textToBe(saveStatusId, "Сохранено в Yandex"));
//        waitForElementAndClick(fileMenuButtonXpath);
//        driver.switchTo().defaultContent();
        return this;
    }

    public YandexDiskTextDocumentPage renameMenuOptionClick() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeXpath));
        new WebDriverWait(driver, 40).until(ExpectedConditions.textToBe(saveStatusId, "Сохранено в Yandex"));
        //waitForElementAndClick(renameMenuOptionId);
        waitForElementAndClick(cheatRename);
        driver.switchTo().defaultContent();
        return this;
    }

    public YandexDiskTextDocumentPage setDocumentName() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeXpath));
        documentName = String.valueOf(System.currentTimeMillis());

        // Is document name typed fully.
        WebElement documentNameField = new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(cheatRename));
        Actions actionsBuilder = new Actions(driver);
        actionsBuilder
                .click(documentNameField)
                .sendKeys(Keys.chord(Keys.CONTROL, Keys.getKeyFromUnicode('a')))
                .sendKeys(Keys.DELETE)
                .sendKeys(documentName).build().perform();
        //documentNameField.click();
        //documentNameField.sendKeys(Keys.chord(Keys.CONTROL, Keys.getKeyFromUnicode('a'), Keys.DELETE));
        //documentNameField.sendKeys(Keys.DELETE);
        //documentNameField.sendKeys(documentName);
        driver.switchTo().defaultContent();
        return this;
    }

    public YandexDiskTextDocumentPage okButtonClick() {
        new WebDriverWait(driver, 20).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframeXpath));

        // Is document name is fully typed in check.
        System.out.println("text = " + driver.findElement(documentNameFieldId).getText());
        new WebDriverWait(driver, 20).until(ExpectedConditions.textToBe(documentNameFieldId, documentName));
        waitForElementAndClick(okButtonId);
        driver.switchTo().defaultContent();
        return this;
    }

    private void writeProperty(String key, String value) {
        FileInputStream fileInputStream;
        Properties property = new Properties();

        try {
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
            property.load(fileInputStream);
            property.setProperty(key, value);

            String host = property.getProperty("db.host");
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
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
