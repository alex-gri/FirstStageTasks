package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.util.DataStorage;
import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

public class FolderService {

    private FolderService() {}

    public static YandexDiskCreatePage createFolder(Folder folder) {
        Log.info("[Folder] Creating a folder with random name");
        return folder.getFolderPage()
                .createButtonClick()
                .createFolderOptionClick()
                .setFolderName(folder.getName())
                .saveButtonClick();
    }

    public static YandexDiskFolderPage deleteDocument() {
        Log.info("[Folder] Removing document from current folder");
        return new YandexDiskFolderPage()
                .selectDocument()
                .deleteButtonClick();
    }

    public static String getFolderName() {
        return (String) DataStorage.getObjectByKey("folder.name");
    }
}
