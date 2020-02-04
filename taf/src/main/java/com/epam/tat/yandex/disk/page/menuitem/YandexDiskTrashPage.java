package com.epam.tat.yandex.disk.page.menuitem;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskTrashPage extends AbstractMenuPage {

    private By emptyTrashButtonXpath = By.xpath("//*[text()='Очистить Корзину']/ancestor::button");
    private By confirmEmptyTrashButtonXpath = By.xpath("//*[text()='Очистить']/ancestor::button");

    public YandexDiskTrashPage emptyTrashButtonClick() {
        Browser.getInstance().waitForAttributeToBe(emptyTrashButtonXpath, "aria-disabled", "false");
        Browser.getInstance().clickNoWait(emptyTrashButtonXpath);
        return this;
    }

    public YandexDiskTrashPage confirmEmptyTrashButtonClick() {
        Log.report("Confirming emptying");
        Browser.getInstance().click(confirmEmptyTrashButtonXpath);
        return this;
    }

    // Clean button is enabled only when there is something in trash.
    public boolean isTrashEmpty() {
        return !Browser.getInstance().waitForVisibilityOfElementLocated(emptyTrashButtonXpath).isEnabled();
    }
}
