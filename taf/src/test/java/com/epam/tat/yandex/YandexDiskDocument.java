package com.epam.tat.yandex;

import com.epam.tat.testbase.TestBase;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;
import com.epam.tat.yandex.disk.page.service.DocumentService;
import com.epam.tat.yandex.disk.page.service.FolderService;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;

import java.sql.Driver;

/**
 * Create new Word document inside that new folder, give it name,
 * past some text inside document (“Hello world!” for example) and save it.
 * Check that you can see document inside appropriate folder
 * and you can reopen it for editing and you see all you information saved correctly.
 */

public class YandexDiskDocument extends TestBase {

    @Test
    public void isDocumentInAppropriateFolderTest() {
        FolderService
                .createFolder()
                .openCreatedFolder();
        DocumentService
                .createDocument()
                .writeToDocument("Hello World!");
        boolean isDocumentInAppropriateFolder = DocumentService
                .renameDocument()
                .closeDocumentTab()
                .isDocumentInAppropriateFolder();

        Assert.assertTrue(isDocumentInAppropriateFolder);
    }

    @Test
    public void isDocumentSavedAndOpenedCorrectlyTest() {
        FolderService
                .createFolder()
                .openCreatedFolder();
        DocumentService
                .createDocument()
                .writeToDocument("Hello World!");
        boolean isDocumentSavedAndOpenedCorrectly = DocumentService
                .renameDocument()
                .closeDocumentTab()
                .openDocument()
                .isTextCorrect();

        Assert.assertTrue(isDocumentSavedAndOpenedCorrectly);
    }

    @Test
    public void isDocumentInTrashOnly() {
        FolderService
                .createFolder()
                .openCreatedFolder();
        DocumentService
                .createDocument()
                .writeToDocument("Hello World!");
        DocumentService
                .renameDocument()
                .closeDocumentTab();
        boolean isDocumentInTrashOnly = FolderService
                .deleteDocument()
                .isDocumentInTrashOnly();

        Assert.assertFalse(isDocumentInTrashOnly);
    }
}
