package com.epam.tat.yandex.disk.page.menuitem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskTrashPage extends AbstractMenuPage {

    private By trashContentTitleXpath = By.xpath("//h1[text()='Корзина']");
    private By emptyTrashButtonXpath = By.xpath("//*[text()='Очистить Корзину']/ancestor::button");
    private By confirmEmptyTrashButtonXpath = By.xpath("//*[text()='Очистить']/ancestor::button");

    public boolean isItTrashPage() {
        return browserInstance.isDisplayed(trashContentTitleXpath);
    }

    public YandexDiskTrashPage emptyTrashButtonClick() {
        browserInstance.click(emptyTrashButtonXpath);
        return this;
    }

    public YandexDiskTrashPage confirmEmptyTrashButtonClick() {
        browserInstance.click(confirmEmptyTrashButtonXpath);
        return this;
    }

    // Clean button is enabled only when there is something in trash.
    public boolean isTrashEmpty() {
        return !browserInstance.waitForVisibilityOfElement(emptyTrashButtonXpath).isEnabled();
    }
}
