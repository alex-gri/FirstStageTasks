package com.epam.tat.yandex.disk.page.menuitem;

import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;
import org.openqa.selenium.By;

public class YandexDiskSharedPage extends AbstractMenuPage {

    private By sharedContentTitle = By.xpath("//h1[text()='Публичные ссылки']");

    public boolean isItSharedPage() {
        return browserInstance.isDisplayed(sharedContentTitle);
    }
}
