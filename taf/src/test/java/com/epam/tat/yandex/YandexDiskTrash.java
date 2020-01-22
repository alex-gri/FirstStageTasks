package com.epam.tat.yandex;

import com.epam.tat.testbase.TestBase;
import com.epam.tat.yandex.disk.page.service.FolderService;
import com.epam.tat.yandex.disk.page.service.TrashService;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

/**
 * Test you can empty trash. Check that document removed completely.
 */

public class YandexDiskTrash extends TestBase {

    @Test
    public void isFolderRemovedFromTrashAfterCleanTest() {
        FolderService
                .createFolder()
                .deleteButtonClick()
                .trashMenuItemClick();
        boolean isFolderRemovedFromTrashAfterClean = TrashService
                .EmptyTrash()
                .isTrashEmpty();

        Assert.assertTrue(isFolderRemovedFromTrashAfterClean);
    }
}
