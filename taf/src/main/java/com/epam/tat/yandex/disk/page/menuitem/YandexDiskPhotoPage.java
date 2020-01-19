package com.epam.tat.yandex.disk.page.menuitem;

import com.epam.tat.yandex.disk.page.base.AbstractMenuPage;
import org.openqa.selenium.By;

public class YandexDiskPhotoPage extends AbstractMenuPage {

    private By photoContentTitle = By.xpath("//h1[text()='Все фотографии']");

    public boolean isItPhotoPage() {
        return browserInstance.isDisplayed(photoContentTitle);
    }
}
