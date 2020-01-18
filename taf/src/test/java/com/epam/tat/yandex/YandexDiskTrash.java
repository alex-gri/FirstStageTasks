package com.epam.tat.yandex;

import com.epam.tat.testbase.TestBase;
import com.epam.tat.yandex.disk.page.service.FolderService;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

/**
 * Test you can empty trash. Check that document removed completely.
 */

public class YandexDiskTrash extends TestBase {

    @Test
    public void isFolderRemovedFromTrashAfterClean() {
        boolean isFolderRemovedFromTrashAfterClean = FolderService
                .createFolder()
                .deleteButtonClick()
                .trashMenuItemClick()
                .emptyTrashButtonClick()
                .confirmEmptyTrashButtonClick()
                .isTrashEmpty();

        Assert.assertTrue(isFolderRemovedFromTrashAfterClean);
    }
}
