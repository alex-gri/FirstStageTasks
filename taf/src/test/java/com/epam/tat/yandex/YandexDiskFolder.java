package com.epam.tat.yandex;


import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.model.builder.FolderBuilder;
import com.epam.tat.testbase.TestBase;
import com.epam.tat.yandex.disk.page.service.FolderService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Check creation of new folder inside Файлы (use unique name for each new folder), check you can visit that folder.
 */

public class YandexDiskFolder extends TestBase {

    @Test
    public void createFolderAndVisitItTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        boolean isFolderCreatedAndVisited = FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder)
                .isFolderVisited(defaultTestFolder);
        Assert.assertTrue(isFolderCreatedAndVisited);
    }
}
