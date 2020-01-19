package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.context.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;

public class DocumentService {

    public static YandexDiskTextDocumentPage createDocument() {
        return new YandexDiskCreatePage(Browser.getInstance().getWrappedDriver())
                .createButtonClick()
                .createTextDocumentOptionClick();
    }

    public static YandexDiskTextDocumentPage renameDocument() {
        return new YandexDiskTextDocumentPage(Browser.getInstance().getWrappedDriver())
                .renameDocumentFieldClick()
                .setDocumentName();
    }
}
