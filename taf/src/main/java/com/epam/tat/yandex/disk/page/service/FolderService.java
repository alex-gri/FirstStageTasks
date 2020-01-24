package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.model.Document;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;

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

    public static YandexDiskFolderPage deleteDocument(Document document) {
        Log.info("[Folder] Removing document from current folder");
        return new YandexDiskFolderPage()
                .selectDocument(document)
                .deleteButtonClick();
    }
}
