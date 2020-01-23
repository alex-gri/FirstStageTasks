package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.framework.logger.Log;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskTrashPage;

public class TrashService {

    private TrashService() {}

    public static YandexDiskTrashPage emptyTrash() {
        Log.info("[Trash] Emptying the trash...");
        return new YandexDiskTrashPage()
                .emptyTrashButtonClick()
                .confirmEmptyTrashButtonClick();
    }
}
