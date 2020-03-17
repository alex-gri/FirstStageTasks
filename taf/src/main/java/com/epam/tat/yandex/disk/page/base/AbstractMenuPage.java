package com.epam.tat.yandex.disk.page.base;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import org.openqa.selenium.By;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskTrashPage;

public abstract class AbstractMenuPage {

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
    }

    public YandexDiskCreatePage createButtonClick() {
        Browser.getInstance().click(createButton);
        return new YandexDiskCreatePage();
    }

    public YandexDiskFolderPage deleteButtonClick() {
        Log.report("Removing the document");
        Browser.getInstance().click(deleteButton);
        return new YandexDiskFolderPage();
    }

    public YandexDiskTrashPage archiveMenuItemClick() {
        Log.report("Moving to Archive page");
        Browser.getInstance().click(archiveMenuItem);
        return new YandexDiskTrashPage();
    }

    public YandexDiskTrashPage filesMenuItemClickAfterClickOnAnotherItem() {
        Log.report("Moving to Files page");

        // Changing current directory to trash first, because current could be Files already.
        Browser.getInstance().click(trashMenuItem);
        Browser.getInstance().click(filesMenuItem);
        return new YandexDiskTrashPage();
    }

    public YandexDiskTrashPage filesMenuItemClick() {
        Log.report("Moving to Files page");
        Browser.getInstance().click(filesMenuItem);
        return new YandexDiskTrashPage();
    }

    public YandexDiskTrashPage historyMenuItemClick() {
        Log.report("Moving to History page");
        Browser.getInstance().click(historyMenuItem);
        return new YandexDiskTrashPage();
    }

    public YandexDiskTrashPage photoMenuItemClick() {
        Log.report("Moving to Photo page");
        Browser.getInstance().click(photoMenuItem);
        return new YandexDiskTrashPage();
    }

    public YandexDiskTrashPage recentMenuItemClick() {
        Log.report("Moving to Recent page");
        Browser.getInstance().click(recentMenuItem);
        return new YandexDiskTrashPage();
    }

    public YandexDiskTrashPage sharedMenuItemClick() {
        Log.report("Moving to Shared page");

        Browser.getInstance().click(sharedMenuItem);
        return new YandexDiskTrashPage();
    }

    public YandexDiskTrashPage trashMenuItemClick() {
        Log.report("Moving to Trash page");

        Browser.getInstance().click(trashMenuItem);
        return new YandexDiskTrashPage();
    }

    public String getContentTitle() {
        Log.report("Getting title of current page's content");
        return Browser.getInstance().getText(contentTitle);
    }

    public void waitForContentTitleToBe(String title) {
        Log.report("Waiting for content title of the current page to be: ");
        Browser.getInstance().waitForAttributeToBe(contentTitle, "title", title);
    }
}
