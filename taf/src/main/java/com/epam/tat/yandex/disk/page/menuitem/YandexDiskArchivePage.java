package com.epam.tat.yandex.disk.page.menuitem;

import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;
import org.openqa.selenium.By;

public class YandexDiskArchivePage extends AbstractMenuPage {

    private By archiveContentTitle = By.xpath("//h1[text()='Архив']");

    public boolean isItArchivePage() {
        return browserInstance.isDisplayed(archiveContentTitle);
    }
}
