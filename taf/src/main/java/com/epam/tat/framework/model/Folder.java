package com.epam.tat.framework.model;

import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;

public class Folder {

    private String name;
    private YandexDiskFolderPage folderPage;

    public Folder() {
        this.folderPage = new YandexDiskFolderPage();
    }

    public YandexDiskFolderPage getFolderPage() {
        return folderPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
