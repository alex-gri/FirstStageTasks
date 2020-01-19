package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.context.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

public class FolderService {

    public static YandexDiskCreatePage createFolder() {
        return new YandexDiskFilesPage(Browser.getInstance().getWrappedDriver())
                .createButtonClick()
                .createFolderOptionClick()
                .setFolderName()
                .saveButtonClick();
    }

    public static YandexDiskFolderPage deleteDocument() {
        return new YandexDiskFolderPage(Browser.getInstance().getWrappedDriver())
                .selectDocument()
                .deleteButtonClick();
    }
}
