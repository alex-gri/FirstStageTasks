package com.epam.tat.yandex.disk.page.menuitem;

import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;

public class YandexDiskFilesPage extends AbstractMenuPage {

    private By filesContentTitle = By.xpath("//h1[text()='Файлы']");

    public boolean isItFilesPage() {
        return browserInstance.isDisplayed(filesContentTitle);
    }
}
