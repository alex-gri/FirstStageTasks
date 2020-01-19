package com.epam.tat.yandex.disk.page.base;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.context.YandexDiskCreatePage;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;
import com.epam.tat.yandex.disk.page.menuitem.*;

public abstract class AbstractMenuPage {

    protected static Browser browserInstance;

    protected By recentMenuItem = By.xpath("//a[@title='Последние']");
    protected By filesMenuItem = By.xpath("//a[@title='Файлы']");
    protected By photoMenuItem = By.xpath("//a[@title='Фото']");
    protected By sharedMenuItem = By.xpath("//a[@title='Общий доступ']");
    protected By historyMenuItem = By.xpath("//a[@title='История']");
    protected By archiveMenuItem = By.xpath("//a[@title='Архив']");
    protected By trashMenuItem = By.xpath("//a[@title='Корзина']");

    protected By createButton = By.xpath("//button[contains(.,'Создать')]");
    protected By deleteButton = By.xpath("//span[text()='Удалить']//ancestor::button");

    public AbstractMenuPage() {
        this.browserInstance = Browser.getInstance();
    }

    public YandexDiskCreatePage createButtonClick() {
        browserInstance.click(createButton);
        return new YandexDiskCreatePage();
    }

    public YandexDiskFolderPage deleteButtonClick() {
        browserInstance.click(deleteButton);
        return new YandexDiskFolderPage();
    }

    public YandexDiskArchivePage archiveMenuItemClick() {
        browserInstance.click(archiveMenuItem);
        return new YandexDiskArchivePage();
    }

    public YandexDiskFilesPage filesMenuItemClickAfterClickOnAnotherItem() {

        // Changing current directory to trash first, because current could be Files already.
        browserInstance.click(trashMenuItem);
        browserInstance.click(filesMenuItem);
        return new YandexDiskFilesPage();
    }

    public YandexDiskFilesPage filesMenuItemClick() {
        browserInstance.click(filesMenuItem);
        return new YandexDiskFilesPage();
    }

    public YandexDiskHistoryPage historyMenuItemClick() {
        browserInstance.click(historyMenuItem);
        return new YandexDiskHistoryPage();
    }

    public YandexDiskPhotoPage photoMenuItemClick() {
        browserInstance.click(photoMenuItem);
        return new YandexDiskPhotoPage();
    }

    public YandexDiskRecentPage recentMenuItemClickAfterClickOnAnotherItem() {

        // Changing current directory to trash first, because current could be Recent already.
        browserInstance.click(trashMenuItem);
        browserInstance.click(recentMenuItem);
        return new YandexDiskRecentPage();
    }

    public YandexDiskSharedPage sharedMenuItemClick() {
        browserInstance.click(sharedMenuItem);
        return new YandexDiskSharedPage();
    }

    public YandexDiskTrashPage trashMenuItemClick() {
        browserInstance.click(trashMenuItem);
        return new YandexDiskTrashPage();
    }
}
