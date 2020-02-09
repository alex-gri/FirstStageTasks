package com.epam.tat.test.yandex.cucumber.disk;

import com.epam.tat.framework.model.Document;
import com.epam.tat.framework.model.Folder;
import com.epam.tat.framework.model.builder.DocumentBuilder;
import com.epam.tat.framework.model.builder.FolderBuilder;
import com.epam.tat.yandex.disk.page.createcontext.YandexDiskCreatePage;
import com.epam.tat.yandex.disk.page.createdelement.YandexDiskTextDocumentPage;
import com.epam.tat.yandex.disk.page.service.DocumentService;
import com.epam.tat.yandex.disk.page.service.FolderService;
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

    @When("user creates folder with <random name>")
    public void userCreatesFolderWithRandomName() {
        defaultTestFolder = new FolderBuilder().setDefaultName().build();
        FolderService.createFolder(defaultTestFolder);
    }

    @And("opens that folder")
    public void opensThatFolder() {
        new YandexDiskCreatePage().openCreatedFolder(defaultTestFolder);
    }

    @And("clicks create text document")
    public void clicksCreateTextDocument() {
        defaultTestDocument = new DocumentBuilder().setDefaultName().setDefaultText().build();
        DocumentService.createDocument(defaultTestDocument);
    }

    @And("closes that document")
    public void closesThatDocument() {
        new YandexDiskTextDocumentPage().closeDocumentTab();
    }

    @Then("user sees document inside folder with <random name>")
    public void userSeesDocumentInsideFolderWithRandomName() {
        String folderNameOfDocument = defaultTestFolder.getFolderPage().getContentTitle();
        assertThat("Name of the folder where document is doesn't match to the name of provided test-folded",
                folderNameOfDocument, is(equalTo(defaultTestFolder.getName())));
    }
}
