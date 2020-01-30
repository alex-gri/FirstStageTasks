package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.model.Document;
import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;

public class DocumentService {

    private DocumentService() {}

    public static YandexDiskTextDocumentPage createDocument(Document document) {
        Log.report("[Document] Creating a document. It opens automatically");
        new YandexDiskCreatePage()
                .createButtonClick()
                .createTextDocumentOptionClick();
        return document.getDocumentPage()
                .writeToDocument(document.getText())
                .renameDocumentFieldClick()
                .setDocumentName(document.getName());
    }
}
