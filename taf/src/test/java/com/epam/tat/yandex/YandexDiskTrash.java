package com.epam.tat.yandex;

import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.model.FolderBuilder;
import com.epam.tat.testbase.TestBase;
import com.epam.tat.yandex.disk.page.service.FolderService;
import com.epam.tat.yandex.disk.page.service.TrashService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test you can empty trash. Check that document removed completely.
 */

public class YandexDiskTrash extends TestBase {

    @Test
    public void isFolderRemovedFromTrashAfterCleanTest() {
        Folder testFolder = new FolderBuilder().setDefaultName().build();
        FolderService
                .createFolder(testFolder)
                .deleteButtonClick()
                .trashMenuItemClick();
        boolean isFolderRemovedFromTrashAfterClean = TrashService
                .emptyTrash()
                .isTrashEmpty();

        Assert.assertTrue(isFolderRemovedFromTrashAfterClean);
    }
}
