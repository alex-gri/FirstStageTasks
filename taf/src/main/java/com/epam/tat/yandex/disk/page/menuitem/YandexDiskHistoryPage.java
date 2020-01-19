package com.epam.tat.yandex.disk.page.menuitem;

import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;
import org.openqa.selenium.By;

public class YandexDiskHistoryPage extends AbstractMenuPage {

    private By historyContentTitle = By.xpath("//h1[text()='История']");

    public boolean isItHistoryPage() {
        return browserInstance.isDisplayed(historyContentTitle);
    }
}
