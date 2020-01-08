package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.menuitems.YandexDiskFilesPage;

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
