package com.epam.tat.test.yandex;

import com.epam.tat.framework.model.Document;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.model.builder.DocumentBuilder;
import com.epam.tat.framework.model.builder.FolderBuilder;
import com.epam.tat.test.testbase.TestBase;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskTrashPage;
import com.epam.tat.yandex.disk.page.service.DocumentService;
import com.epam.tat.yandex.disk.page.service.FolderService;
import com.epam.tat.yandex.disk.page.service.TrashService;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class YandexDisk extends TestBase {

    @Test (description = "Create document and check if it's in the folder where we wanted it to be")
    public void isDocumentInAppropriateFolderTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        Document defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();

        FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder);
        DocumentService
                .createDocument(defaultTestDocument); // Document opens automatically.
        String folderNameOfDocument = DocumentService
                .setDocumentNameTo(defaultTestDocument)
                .writeToDocument(defaultTestDocument.getText())
                .closeDocumentTab()
                .getContentTitle();

        assertThat("Name of the folder where document is doesn't match to the name of provided test-folded",
                   folderNameOfDocument, is(equalTo(defaultTestFolder.getName())));
    }

    @Test (description = "Create document, type text in it, save it, open and check if text saved correctly")
    public void isDocumentSavedAndOpenedCorrectlyTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        Document defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();

        FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder);
        DocumentService
                .createDocument(defaultTestDocument); // Document opens automatically.
        String documentText = DocumentService
                .setDocumentNameTo(defaultTestDocument)
                .writeToDocument(defaultTestDocument.getText())
                .closeDocumentTab()
                .openDocument(defaultTestDocument)
                .getDocumentText();

        assertThat("Text in document after reopening doesn't match to the text given earlier at creating stage",
                   documentText, is(equalTo(defaultTestDocument.getText())));
    }

    @Test (description = "Create document, delete it and check if it's visible only in trash")
    public void isDocumentInTrashOnlyTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        Document defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();

        FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder);
        DocumentService
                .createDocument(defaultTestDocument) // Document opens automatically.
                .closeDocumentTab();
        boolean isDocumentInTrashOnly = FolderService
                .deleteDocument(defaultTestDocument)
                .isDocumentInTrashOnly(defaultTestDocument);

        Assert.assertTrue(isDocumentInTrashOnly, "Document was not deleted from it's folder");
    }

    @Test (description = "Create folder, open it and check if it has the name that we wanted")
    public void createFolderAndVisitItTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        String openedFolderName = FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder)
                .getContentTitle();
        assertThat("Name of opened folder doesn't match to the name of provided test-folder",
                   openedFolderName, is(equalTo(defaultTestFolder.getName())));
    }

    @Test (description = "Click Archive in menu and check if it opens")
    public void archiveMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskTrashPage()
                .archiveMenuItemClick()
                .getContentTitle();
        assertThat("Opened page is not Archive", contentTitle, is("Архив"));
    }

    @Test (description = "Click Files in menu and check if it opens")
    public void filesMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskTrashPage()
                .filesMenuItemClickAfterClickOnAnotherItem()
                .getContentTitle();
        assertThat("Opened page is not Files", contentTitle, is("Файлы"));
    }

    @Test (description = "Click History in menu and check if it opens")
    public void historyMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskTrashPage()
                .historyMenuItemClick()
                .getContentTitle();
        assertThat("Opened page is not History", contentTitle, is("История"));
    }

    @Test (description = "Click Photo in menu and check if it opens")
    public void photoMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskTrashPage()
                .photoMenuItemClick()
                .getContentTitle();
        assertThat("Opened page is not Photo", contentTitle, is("Все фотографии"));
    }

    @Test (description = "Click Recent in menu and check if it opens")
    public void recentMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskTrashPage()
                .recentMenuItemClick()
                .getContentTitle();
        assertThat("Opened page is not Recent", contentTitle, is("Последние файлы"));
    }

    @Test (description = "Click Shared in menu and check if it opens")
    public void sharedMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskTrashPage()
                .sharedMenuItemClick()
                .getContentTitle();
        assertThat("Opened page is not Shared", contentTitle, is("Публичные ссылки"));
    }

    @Test (description = "Click Trash in menu and check if it opens")
    public void trashMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskTrashPage()
                .trashMenuItemClick()
                .getContentTitle();
        assertThat("Opened page is not trash", contentTitle, is("Корзина"));
    }

    @Test (description = "Create folder, delete it, empty trash and check if trash is empty")
    public void isFolderRemovedFromTrashAfterEmptyTest() {
        Folder testFolder = new FolderBuilder().setDefaultName().build();
        FolderService
                .createFolder(testFolder)
                .deleteButtonClick()
                .trashMenuItemClick();
        boolean isFolderRemovedFromTrashAfterClean = TrashService
                .emptyTrash()
                .isTrashEmpty();

        Assert.assertTrue(isFolderRemovedFromTrashAfterClean, "Trash was not emptied");
    }
}
