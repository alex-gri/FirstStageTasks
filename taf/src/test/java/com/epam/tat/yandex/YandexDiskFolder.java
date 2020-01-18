package com.epam.tat.yandex;


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
        boolean isFolderCreatedAndVisited = FolderService
                .createFolder()
                .openCreatedFolder()
                .isFolderVisited();
        Assert.assertTrue(isFolderCreatedAndVisited);
    }
}
