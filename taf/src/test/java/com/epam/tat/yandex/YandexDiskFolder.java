package com.epam.tat.yandex;

import com.epam.tat.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

/**
 * Check creation of new folder inside Файлы (use unique name for each new folder), check you can visit that folder.
 */

public class YandexDiskFolder extends TestBase {

    @Test
    public void createFolderAndVisitItTest() {
        boolean isFolderCreatedAndVisited = new YandexDiskFilesPage(driver)
                .createButtonClick()
                .createFolderOptionClick()
                .setFolderName()
                .saveButtonClick()
                .openCreatedFolder()
                .isFolderVisited();

        Assert.assertTrue(isFolderCreatedAndVisited);
    }
}
