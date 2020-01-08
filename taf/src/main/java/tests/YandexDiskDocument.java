package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.menuitems.YandexDiskFilesPage;

/**
 * Create new Word document inside that new folder, give it name,
 * past some text inside document (“Hello world!” for example) and save it.
 * Check that you can see document inside appropriate folder
 * and you can reopen it for editing and you see all you information saved correctly.
 */

public class YandexDiskDocument extends TestBase {

    @Test
    public void isDocumentInAppropriateFolderTest() {
        boolean isDocumentInAppropriateFolder = new YandexDiskFilesPage(driver)
                .createButtonClick()
                .createFolderOptionClick()
                .setFolderName()
                .saveButtonClick()
                .openCreatedFolder()
                .createButtonClick()
                .createTextDocumentOptionClick()
                .writeToDocument("Hello World!")
                .renameDocumentFieldClick()
                .setDocumentName()
                .closeDocumentTab()
                .isDocumentInAppropriateFolder();

        Assert.assertTrue(isDocumentInAppropriateFolder);
    }

    @Test
    public void isDocumentSavedAndOpenedCorrectlyTest() {
        boolean isDocumentSavedAndOpenedCorrectly = new YandexDiskFilesPage(driver)
                .createButtonClick()
                .createFolderOptionClick()
                .setFolderName()
                .saveButtonClick()
                .openCreatedFolder()
                .createButtonClick()
                .createTextDocumentOptionClick()
                .writeToDocument("Hello World!")
                .renameDocumentFieldClick()
                .setDocumentName()
                .closeDocumentTab()
                .openDocument()
                .isTextCorrect();

        Assert.assertTrue(isDocumentSavedAndOpenedCorrectly);
    }

    @Test
    public void isDocumentInTrashOnly() {
        boolean isDocumentInTrashOnly = new YandexDiskFilesPage(driver)
                .createButtonClick()
                .createFolderOptionClick()
                .setFolderName()
                .saveButtonClick()
                .openCreatedFolder()
                .createButtonClick()
                .createTextDocumentOptionClick()
                .writeToDocument("Hello World!")
                .renameDocumentFieldClick()
                .setDocumentName()
                .closeDocumentTab()
                .selectDocument()
                .deleteButtonClick()
                .isDocumentInTrashOnly();

        Assert.assertFalse(isDocumentInTrashOnly);
    }
}
