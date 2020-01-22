package com.epam.tat.yandex.disk.page.service;

import com.epam.tat.yandex.disk.page.menuitem.YandexDiskTrashPage;

public class TrashService {

    public static YandexDiskTrashPage EmptyTrash() {
        return new YandexDiskTrashPage()
                .emptyTrashButtonClick()
                .confirmEmptyTrashButtonClick();
    }
}
