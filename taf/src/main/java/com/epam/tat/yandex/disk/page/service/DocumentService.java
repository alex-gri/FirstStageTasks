package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;

public class DocumentService {

    public static YandexDiskTextDocumentPage createDocument() {
        return new YandexDiskCreatePage()
                .createButtonClick()
                .createTextDocumentOptionClick();
    }

    public static YandexDiskTextDocumentPage renameDocument() {
        return new YandexDiskTextDocumentPage()
                .renameDocumentFieldClick()
                .setDocumentName();
    }
}
