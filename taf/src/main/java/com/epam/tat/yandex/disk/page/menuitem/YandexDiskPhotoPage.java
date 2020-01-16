package com.epam.tat.yandex.disk.page.menuitem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskPhotoPage {

    private WebDriver driver;

    private By photoContentTitle = By.xpath("//h1[text()='Все фотографии']");

    public YandexDiskPhotoPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItPhotoPage() {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(photoContentTitle))
                .isDisplayed();
    }
}
