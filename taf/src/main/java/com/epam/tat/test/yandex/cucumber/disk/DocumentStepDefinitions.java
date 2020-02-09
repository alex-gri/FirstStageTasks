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
import com.epam.tat.yandex.disk.page.service.DocumentService;
import com.epam.tat.yandex.disk.page.service.FolderService;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class DocumentStepDefinitions {

    private Folder defaultTestFolder;
    private Document defaultTestDocument;

    @Given("user is on Files page")
    public void userIsOnFilesPage() {
        // It is already opened in BeforeClass
    }

    @When("user creates folder with <random folder name>")
    public void userCreatesFolderWithRandomName() {
        defaultTestFolder = new FolderBuilder().setDefaultName().build();
        FolderService.createFolder(defaultTestFolder);
    }

    @And("opens that folder")
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

    @And("user types <text> into it")
    public void userTypesTextIntoIt() {
        new YandexDiskTextDocumentPage().writeToDocument(defaultTestDocument.getText());
    }

    @And("user has sample of document that consist of text and name")
    public void userHasSampleOfDocumentThatIncludesTextAndName() {
        defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();
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

    @After
    public void returnFilesPage() {
        CucumberDisk.closeAllTabsExceptFirst();
        new PassportYandexAuthorizationPage()
                .openYandexDiskFilesPage()
                .filesMenuItemClick()
                .waitForContentTitleToBe("Файлы");
    }
}
