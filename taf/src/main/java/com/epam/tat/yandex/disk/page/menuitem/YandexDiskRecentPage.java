package com.epam.tat.yandex.disk.page.menuitem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskRecentPage {

    private WebDriver driver;

    private By recentContentTitle = By.xpath("//h1[text()='Последние файлы']");

    public YandexDiskRecentPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItRecentPage() {
            return new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOfElementLocated(recentContentTitle))
                    .isDisplayed();
    }
}
