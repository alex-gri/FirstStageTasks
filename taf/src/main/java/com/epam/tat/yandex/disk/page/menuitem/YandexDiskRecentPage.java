package com.epam.tat.yandex.disk.page.menuitem;

import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;
import org.openqa.selenium.By;

public class YandexDiskRecentPage extends AbstractMenuPage {

    private By recentContentTitle = By.xpath("//h1[text()='Последние файлы']");

    public boolean isItRecentPage() {
            return browserInstance.isDisplayed(recentContentTitle);
    }
}
