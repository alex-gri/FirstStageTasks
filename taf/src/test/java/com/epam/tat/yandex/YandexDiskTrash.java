package com.epam.tat.yandex;

import com.epam.tat.testbase.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

/**
 * Test you can empty trash. Check that document removed completely.
 */

public class YandexDiskTrash extends TestBase {

    @Test
    public void isFolderRemovedFromTrashAfterClean() {
        boolean isFolderRemovedFromTrashAfterClean = new YandexDiskFilesPage(driver)
                .createButtonClick()
                .createFolderOptionClick()
                .setFolderName()
                .saveButtonClick()
                .deleteButtonClick()
                .trashMenuItemClick()
                .emptyTrashButtonClick()
                .confirmEmptyTrashButtonClick()
                .isTrashEmpty();

        Assert.assertTrue(isFolderRemovedFromTrashAfterClean);
    }
}