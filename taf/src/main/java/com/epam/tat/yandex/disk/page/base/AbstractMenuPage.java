package com.epam.tat.yandex.disk.page.base;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskTrashPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskArchivePage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskHistoryPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskPhotoPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskRecentPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskSharedPage;

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
    protected By contentTitle = By.tagName("h1");

    public AbstractMenuPage() {
        this.browserInstance = Browser.getInstance();
    }

    public YandexDiskCreatePage createButtonClick() {
        browserInstance.click(createButton);
        return new YandexDiskCreatePage();
    }

    public YandexDiskFolderPage deleteButtonClick() {
        Log.report("Removing the document");
        browserInstance.click(deleteButton);
        return new YandexDiskFolderPage();
    }

    public YandexDiskArchivePage archiveMenuItemClick() {
        Log.report("Moving to Archive page");
        browserInstance.click(archiveMenuItem);
        return new YandexDiskArchivePage();
    }

    public YandexDiskFilesPage filesMenuItemClickAfterClickOnAnotherItem() {
        Log.report("Moving to Files page");

        // Changing current directory to trash first, because current could be Files already.
        browserInstance.click(trashMenuItem);
        browserInstance.click(filesMenuItem);
        return new YandexDiskFilesPage();
    }

    public YandexDiskFilesPage filesMenuItemClick() {
        Log.report("Moving to Files page");
        browserInstance.click(filesMenuItem);
        return new YandexDiskFilesPage();
    }

    public YandexDiskHistoryPage historyMenuItemClick() {
        Log.report("Moving to History page");
        browserInstance.click(historyMenuItem);
        return new YandexDiskHistoryPage();
    }

    public YandexDiskPhotoPage photoMenuItemClick() {
        Log.report("Moving to Photo page");
        browserInstance.click(photoMenuItem);
        return new YandexDiskPhotoPage();
    }

    public YandexDiskRecentPage recentMenuItemClickAfterClickOnAnotherItem() {
        Log.report("Moving to Recent page");

        // Changing current directory to trash first, because current could be Recent already.
        browserInstance.click(trashMenuItem);
        browserInstance.click(recentMenuItem);
        return new YandexDiskRecentPage();
    }

    public YandexDiskSharedPage sharedMenuItemClick() {
        Log.report("Moving to Shared page");

        browserInstance.click(sharedMenuItem);
        return new YandexDiskSharedPage();
    }

    public YandexDiskTrashPage trashMenuItemClick() {
        Log.report("Moving to Trash page");

        browserInstance.click(trashMenuItem);
        return new YandexDiskTrashPage();
    }

    public String getContentTitle() {
        Log.report("Getting title of current page's content");
        return browserInstance.getText(contentTitle);
    }
}
