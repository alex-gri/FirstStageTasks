package com.epam.tat.yandex;

import com.epam.tat.framework.model.Document;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.model.builder.DocumentBuilder;
import com.epam.tat.framework.model.builder.FolderBuilder;
import com.epam.tat.testbase.TestBase;
import com.epam.tat.yandex.disk.page.menuitem.YandexDiskFilesPage;
import com.epam.tat.yandex.disk.page.service.DocumentService;
import com.epam.tat.yandex.disk.page.service.FolderService;
import com.epam.tat.yandex.disk.page.service.TrashService;
import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class YandexDisk extends TestBase {
    @Test
    public void isDocumentInAppropriateFolderTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        Document defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();

        FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder);
        String folderNameOfDocument = DocumentService
                .createDocument(defaultTestDocument) // Document opens automatically.
                .closeDocumentTab()
                .getContentTitle();

        assertThat(folderNameOfDocument, is(equalTo(defaultTestFolder.getName())));
    }

    @Test
    public void isDocumentSavedAndOpenedCorrectlyTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        Document defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();

        FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder);
        String documentText = DocumentService
                .createDocument(defaultTestDocument) // Document opens automatically.
                .closeDocumentTab()
                .openDocument(defaultTestDocument)
                .getDocumentText();

        assertThat(documentText, is(equalTo(defaultTestDocument.getText())));
    }

    @Test
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

        Assert.assertTrue(isDocumentInTrashOnly);
    }

    @Test
    public void createFolderAndVisitItTest() {
        Folder defaultTestFolder = new FolderBuilder().setDefaultName().build();
        String openedFolderName = FolderService
                .createFolder(defaultTestFolder)
                .openCreatedFolder(defaultTestFolder)
                .getContentTitle();
        assertThat(openedFolderName, CoreMatchers.is(CoreMatchers.equalTo(defaultTestFolder.getName())));
    }

    @Test
    public void archiveMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .archiveMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("Архив"));
    }

    @Test
    public void filesMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .filesMenuItemClickAfterClickOnAnotherItem()
                .getContentTitle();
        assertThat(contentTitle, is("Файлы"));
    }

    @Test
    public void historyMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .historyMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("История"));
    }

    @Test
    public void photoMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .photoMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("Все фотографии"));
    }

    @Test
    public void recentMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .recentMenuItemClickAfterClickOnAnotherItem()
                .getContentTitle();
        assertThat(contentTitle, is("Последние файлы"));
    }

    @Test
    public void sharedMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .sharedMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("Публичные ссылки"));
    }

    @Test
    public void trashMenuItemLeadsToValidPageTest() {
        String contentTitle = new YandexDiskFilesPage()
                .trashMenuItemClick()
                .getContentTitle();
        assertThat(contentTitle, is("Корзина"));
    }

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
