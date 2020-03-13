package com.epam.tat.test.yandex.cucumber.disk;

import com.epam.tat.framework.model.Document;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.model.builder.DocumentBuilder;
import com.epam.tat.framework.model.builder.FolderBuilder;
import com.epam.tat.framework.runner.CucumberDisk;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskFolderPage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;
import com.epam.tat.yandex.disk.page.menuitem.*;
import com.epam.tat.yandex.disk.page.service.DocumentService;
import com.epam.tat.yandex.disk.page.service.FolderService;
import com.epam.tat.yandex.disk.page.service.TrashService;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.testng.Assert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FilesStepDefinitions {

    private Folder defaultTestFolder;
    private Document defaultTestDocument;

    @After
    public void returnFilesPage() {
        CucumberDisk.closeAllTabsExceptFirst();
        new PassportYandexAuthorizationPage()
                .openYandexDiskFilesPage()
                .filesMenuItemClick()
                .waitForContentTitleToBe("Файлы");
    }

    @Given("user is on Files page")
    public void userIsOnFilesPage() {
        // It is already opened in BeforeClass
        defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();
    }

    @When("user creates folder that named randomly")
    public void userCreatesFolderWithName() {
        defaultTestFolder = new FolderBuilder().setDefaultName().build();
        FolderService.createFolder(defaultTestFolder);
    }

    @And("^(?:opens that folder|clicks open folder)$")
    public void opensThatFolder() {
        new YandexDiskCreatePage().openCreatedFolder(defaultTestFolder);
    }

    @And("creates text document")
    public void clicksCreateTextDocument() {
        DocumentService.createDocument(defaultTestDocument);
    }

    @And("closes that document")
    public void closesThatDocument() {
        new YandexDiskTextDocumentPage().closeDocumentTab();
    }

    @Then("user sees document inside folder with <random folder name>")
    public void userSeesDocumentInsideFolderWithRandomName() {
        String folderNameOfDocument = new YandexDiskFolderPage().getContentTitle();
        assertThat("Name of the folder where document is doesn't match to the name of provided test-folded",
                folderNameOfDocument, is(equalTo(defaultTestFolder.getName())));
    }

    @And("user sets <random document name>")
    public void userSetsRandomDocumentName() {
        DocumentService.setDocumentNameTo(defaultTestDocument);
    }

    @And("opens that document")
    public void opensThatDocument() {
        defaultTestFolder.getFolderPage().openDocument(defaultTestDocument);
    }

    @Then("user sees <text> that he typed earlier")
    public void userSeesText() {
        String documentText = defaultTestDocument.getDocumentPage().getDocumentText();
        assertThat("Text in document after reopening doesn't match to the text given earlier at creating stage",
                documentText, is(equalTo(defaultTestDocument.getText())));
    }

    @And("deletes that document")
    public void deletesThatDocument() {
        FolderService.deleteDocument(defaultTestDocument);
    }

    @Then("user should not see document in the current folder")
    public void userShouldNotSeeDocumentInTheCurrentFolder() {
        boolean isDocumentInCurrentFolder = new YandexDiskFolderPage().isDocumentPresent(defaultTestDocument);
        if (isDocumentInCurrentFolder) {
        Assert.assertFalse(isDocumentInCurrentFolder, "Document was not deleted from it's folder");
        }
    }

    @Then("user should see correct <random folder name> in the title")
    public void userShouldSeeCorrectRandomFolderNameInTheTitle() {
        String openedFolderName = new YandexDiskFolderPage()
                .getContentTitle();
        assertThat("Name of opened folder doesn't match to the name of provided test-folder",
                openedFolderName, is(equalTo(defaultTestFolder.getName())));
    }

    @But("can see it in the trash")
    public void canSeeItInTheTrash() {
        new YandexDiskFolderPage().trashMenuItemClick();
        boolean isDocumentInTrash = new YandexDiskFolderPage().isDocumentInTrashOnly(defaultTestDocument);
        Assert.assertTrue(isDocumentInTrash, "There is no document in trash");
    }

    @When("user clicks <Archive>")
    public void userClicksArchive() {
        new YandexDiskFilesPage().archiveMenuItemClick();
    }

    @Then("he reaches the <Archive> page")
    public void heReachesTheArchivePage() {
        String contentTitle = new YandexDiskArchivePage().getContentTitle();
        assertThat("Opened page is not Archive", contentTitle, is("Архив"));
    }

    @And("user clicks <Files>")
    public void userClicksFiles() {
        new YandexDiskFilesPage().filesMenuItemClick();
    }

    @Then("he reaches the <Files> page")
    public void heReachesTheFilesPage() {
        String contentTitle = new YandexDiskFilesPage().getContentTitle();
        assertThat("Opened page is not Files", contentTitle, is("Файлы"));
    }

    @When("user clicks <Photo>")
    public void userClicksPhoto() {
        new YandexDiskFilesPage().photoMenuItemClick();
    }

    @Then("he reaches the <Photo> page")
    public void heReachesThePhotoPage() {
        String contentTitle = new YandexDiskPhotoPage().getContentTitle();
        assertThat("Opened page is not Photo", contentTitle, is("Все фотографии"));
    }

    @When("user clicks <Recent>")
    public void userClicksRecent() {
        new YandexDiskFilesPage().recentMenuItemClick();
    }

    @Then("he reaches the <Recent> page")
    public void heReachesTheRecentPage() {
        String contentTitle = new YandexDiskRecentPage().getContentTitle();
        assertThat("Opened page is not Recent", contentTitle, is("Последние файлы"));
    }

    @When("user clicks <Shared>")
    public void userClicksShared() {
        new YandexDiskFilesPage().sharedMenuItemClick();
    }

    @Then("he reaches the <Shared> page")
    public void heReachesTheSharedPage() {
        String contentTitle = new YandexDiskSharedPage().getContentTitle();
        assertThat("Opened page is not Shared", contentTitle, is("Публичные ссылки"));
    }

    @When("user clicks <Trash>")
    public void userClicksTrash() {
        new YandexDiskFilesPage().trashMenuItemClick();
    }

    @Then("he reaches the <Trash> page")
    public void heReachesTheTrashPage() {
        String contentTitle = new YandexDiskTrashPage().getContentTitle();
        assertThat("Opened page is not trash", contentTitle, is("Корзина"));
    }

    @When("user clicks <History>")
    public void userClicksHistory() {
        new YandexDiskFilesPage().historyMenuItemClick();
    }

    @Then("he reaches the <History> page")
    public void heReachesTheHistoryPage() {
        String contentTitle = new YandexDiskHistoryPage().getContentTitle();
        assertThat("Opened page is not History", contentTitle, is("История"));
    }


    @And("deletes that folder")
    public void deletesThatFolder() {
        new YandexDiskFolderPage().deleteButtonClick();
    }

    @And("user clicks Empty Trash")
    public void userClicksEmptyTrash() {
        TrashService.emptyTrash();
    }

    @Then("trash should be empty")
    public void trashShouldBeEmpty() {
        boolean isTrashEmpty = new YandexDiskTrashPage()
                .isTrashEmpty();
        Assert.assertTrue(isTrashEmpty, "Trash was not emptied");
    }

    @And("^user types \"(.*)\" into it$")
    public void userTypesTextIntoIt(String text) {
        defaultTestDocument.setText(text);
        new YandexDiskTextDocumentPage().writeToDocument(text);
    }

    @When("^user creates folder with (.*)$")
    public void userCreatesFolderWithFolderName(String outlinedFolderName) {
        defaultTestFolder = new FolderBuilder().setName(outlinedFolderName).build();
        FolderService.createFolder(defaultTestFolder);
    }

    @Then("^user should see correct (.*) in the title$")
    public void userShouldSeeCorrectFolderNameInTheTitle(String outlinedFolderName) {
        String openedFolderName = new YandexDiskFolderPage()
                .getContentTitle();
        assertThat("Name of opened folder doesn't match to the name of provided test-folder",
                openedFolderName, is(equalTo(outlinedFolderName)));
    }
}
