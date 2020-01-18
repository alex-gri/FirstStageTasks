package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.driver.DriverSingleton;
import com.epam.tat.yandex.disk.page.context.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;

public class DocumentService {

    public static YandexDiskTextDocumentPage createDocument() {
        return new YandexDiskCreatePage(DriverSingleton.getDriver())
                .createButtonClick()
                .createTextDocumentOptionClick();
    }

    public static YandexDiskTextDocumentPage renameDocument() {
        return new YandexDiskTextDocumentPage(DriverSingleton.getDriver())
                .renameDocumentFieldClick()
                .setDocumentName();
    }
}
