package pages.yandexdisk;

import customconditions.PageLoadingIsCompletedCondition;
import customconditions.TextSelectedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.yandexdisk.menuitempages.YandexDiskFolderContentPage;

public class YandexDiskCreatePage {

    private WebDriver driver;

    private By createFolderOption = new By.ByXPath("//button[contains(.,'Папку')]");
    private By folderNameField = new By.ByXPath("//form[@class='rename-dialog__rename-form']//input");
    private By saveButton = new By.ByXPath("//button[contains(.,'Сохранить')]");
    private By createdFolder = new By.ByXPath("//div[@class='listing-item listing-item_theme_tile listing-item_size_m listing-item_type_dir listing-item_selected js-prevent-deselect']");

    private String folderName;

    public YandexDiskCreatePage(WebDriver driver) {
        this.driver = driver;
        new WebDriverWait(driver,30)
                .until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
    }

    public YandexDiskCreatePage createFolderOptionClick() {
        waitForElementAndClick(createFolderOption);
        return this;
    }

    public YandexDiskCreatePage setFolderName() {
        folderName = String.valueOf(System.currentTimeMillis());

        // Is default folder name "Новая папка" is selected check.
        new WebDriverWait(driver, 10).until(TextSelectedCondition.isDefaultFolderNameSelected());
        driver.findElement(folderNameField).sendKeys(folderName);
        return this;
    }

    public YandexDiskCreatePage saveButtonClick() {

        // Is folder name is fully typed in check.
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.attributeToBe(folderNameField, "value", folderName));
        waitForElementAndClick(saveButton);
        return this;
    }

    public YandexDiskFolderContentPage openCreatedFolder() {
        Actions action = new Actions(driver);
        action.doubleClick(new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(createdFolder))).build().perform();
        return new YandexDiskFolderContentPage(driver, folderName);
    }

    private void waitForElementAndClick(By elementXpath) {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(elementXpath))
                .click();
    }
}
