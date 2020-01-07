package pages.yandexdisk;

import customconditions.TextSelectedCondition;
import iohelper.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.yandexdisk.createdelements.YandexDiskFolderPage;
import pages.yandexdisk.createdelements.YandexDiskTextDocumentPage;

import java.util.Random;

public class YandexDiskCreatePage extends AbstractMenuPage {

    private Random random = new Random();

    private By createFolderOption = By.xpath("//button[contains(.,'Папку')]");
    private By createTextDocumentOption = By.xpath("//button[contains(.,'Текстовый документ')]");
    private By folderNameField = By.xpath("//form[@class='rename-dialog__rename-form']//input");
    private By saveButton = By.xpath("//button[contains(.,'Сохранить')]");
    private By createdFolder = By.xpath("//div[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_dir listing-item_selected js-prevent-deselect']");

    private String folderName;

    public YandexDiskCreatePage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskCreatePage createFolderOptionClick() {
        waitForElementAndClick(createFolderOption);
        return this;
    }

    public YandexDiskTextDocumentPage createTextDocumentOptionClick() {
        waitForElementAndClick(createTextDocumentOption);
        switchDriverToTab(1);
        return new YandexDiskTextDocumentPage(driver);
    }

    public YandexDiskCreatePage setFolderName() {

        // Is default folder name "Новая папка" selected check.
        new WebDriverWait(driver, 10).until(TextSelectedCondition.isDefaultNameSelected("Новая папка"));

        folderName = String.valueOf(Math.abs(random.nextInt()));
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(folderNameField))
                .sendKeys(folderName);
        return this;
    }

    public YandexDiskCreatePage saveButtonClick() {

        // Is folder name is fully typed in check.
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.attributeToBe(folderNameField, "value", folderName));
        waitForElementAndClick(saveButton);
        PropertyManager.writeProperty("folder.name", folderName);
        return this;
    }

    public YandexDiskFolderPage openCreatedFolder() {
        Actions action = new Actions(driver);
        action.doubleClick(new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(createdFolder))).build().perform();
        return new YandexDiskFolderPage(driver, folderName);
    }
}
