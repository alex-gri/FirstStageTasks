package com.epam.tat.yandex.disk.page.menuitem;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskTrashPage extends AbstractMenuPage {

    private By emptyTrashButtonXpath = By.xpath("//div[@class='listing-head__additional-actions']/button");
    private By confirmEmptyTrashButtonXpath = By.xpath("//div[@class='confirmation-dialog__footer']/button[2]");
    private By termOfEmptiness = By.xpath("//div[@class='listing__items']/div[@class='listing-item listing-item_theme_tile-empty listing-item_size_m' and position()=1]");

    public YandexDiskTrashPage emptyTrashButtonClick() {
        Browser.getInstance().waitForAttributeToBe(emptyTrashButtonXpath, "aria-disabled", "false");
        Browser.getInstance().clickNoWait(emptyTrashButtonXpath);
        return this;
    }

    public YandexDiskTrashPage confirmEmptyTrashButtonClick() {
        Log.logAndReport("Confirming emptying");
        Browser.getInstance().click(confirmEmptyTrashButtonXpath);
        return this;
    }

    // Checking that first cell of file-grid is free.
    public boolean isTrashEmpty() {
        return Browser.getInstance().isPresentAtWait(termOfEmptiness);
    }
}
