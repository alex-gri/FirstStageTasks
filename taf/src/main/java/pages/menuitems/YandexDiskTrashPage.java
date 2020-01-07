package pages.menuitems;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AbstractMenuPage;

public class YandexDiskTrashPage extends AbstractMenuPage {

    private By trashContentTitleXpath = By.xpath("//h1[text()='Корзина']");
    private By emptyTrashButtonXpath = By.xpath("//*[text()='Очистить Корзину']/ancestor::button");
    private By confirmEmptyTrashButtonXpath = By.xpath("//*[text()='Очистить']/ancestor::button");

    public YandexDiskTrashPage(WebDriver driver) {
        super(driver);
    }

    public boolean isItTrashPage() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(trashContentTitleXpath))
                .isDisplayed();
    }

    public YandexDiskTrashPage emptyTrashButtonClick() {
        waitForElementAndClick(emptyTrashButtonXpath);
        return this;
    }

    public YandexDiskTrashPage confirmEmptyTrashButtonClick() {
        waitForElementAndClick(confirmEmptyTrashButtonXpath);
        return this;
    }

    // Clean button is enabled only when there is something in trash.
    public boolean isTrashEmpty() {
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.presenceOfElementLocated(emptyTrashButtonXpath));
        return !driver.findElement(emptyTrashButtonXpath).isEnabled();
    }
}
