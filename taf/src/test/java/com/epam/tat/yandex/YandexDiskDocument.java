package com.epam.tat.yandex;

import com.epam.tat.framework.model.Document;
import com.epam.tat.framework.model.builder.DocumentBuilder;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.model.builder.FolderBuilder;
import com.epam.tat.testbase.TestBase;
import com.epam.tat.yandex.disk.page.service.DocumentService;
import com.epam.tat.yandex.disk.page.service.FolderService;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Create new Word document inside that new folder, give it name,
 * past some text inside document (“Hello world!” for example) and save it.
 * Check that you can see document inside appropriate folder
 * and you can reopen it for editing and you see all you information saved correctly.
 */

public class YandexDiskDocument extends TestBase {

    @Test
    public void isDocumentInAppropriateFolderTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        Document defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();

        FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder);
        boolean isDocumentInAppropriateFolder = DocumentService
                .createDocument(defaultTestDocument) // Document opens automatically.
                .closeDocumentTab()
                .isDocumentInAppropriateFolder(defaultTestDocument, defaultTestFolder);

        Assert.assertTrue(isDocumentInAppropriateFolder);
    }

    @Test
    public void isDocumentSavedAndOpenedCorrectlyTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        Document defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();

        FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder);
        boolean isDocumentSavedAndOpenedCorrectly = DocumentService
                .createDocument(defaultTestDocument) // Document opens automatically.
                .closeDocumentTab()
                .openDocument(defaultTestDocument)
                .isTextCorrect(defaultTestDocument);

        Assert.assertTrue(isDocumentSavedAndOpenedCorrectly);
    }

    @Test
    public void isDocumentInTrashOnlyTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        Document defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();

        FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder);
        DocumentService
                .createDocument(defaultTestDocument)
                .closeDocumentTab();
        boolean isDocumentInTrashOnly = FolderService
                .deleteDocument(defaultTestDocument)
                .isDocumentInTrashOnly(defaultTestDocument);

        Assert.assertTrue(isDocumentInTrashOnly);
    }
}
