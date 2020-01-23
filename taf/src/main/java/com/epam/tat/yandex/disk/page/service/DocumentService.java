package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.util.DataStorage;
import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;

public class DocumentService {

    private DocumentService() {}

    public static YandexDiskTextDocumentPage createDocument() {
        Log.info("[Document] Creating a document");
        return new YandexDiskCreatePage()
                .createButtonClick()
                .createTextDocumentOptionClick();
    }

    public static YandexDiskTextDocumentPage renameDocument() {
        Log.info("[Document] Renaming a document with random name");
        return new YandexDiskTextDocumentPage()
                .renameDocumentFieldClick()
                .setDocumentName();
    }

    public static String getDocumentName() {
        return (String) DataStorage.getObjectByKey("document.name");
    }
}
