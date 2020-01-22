package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

public class FolderService {

    public static YandexDiskCreatePage createFolder() {
        return new YandexDiskFilesPage()
                .createButtonClick()
                .createFolderOptionClick()
                .setFolderName()
                .saveButtonClick();
    }

    public static YandexDiskFolderPage deleteDocument() {
        return new YandexDiskFolderPage()
                .selectDocument()
                .deleteButtonClick();
    }
}
