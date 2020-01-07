package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.YandexDiskHomePage;

/**
 * Create new Word document inside that new folder, give it name,
 * past some text inside document (“Hello world!” for example) and save it.
 * Check that you can see document inside appropriate folder
 * and you can reopen it for editing and you see all you information saved correctly.
 */

public class YandexDiskDocument {

    private final String LOGIN = "taf.alexander.gritsok";
    private final String PASSWORD = "WebDriverGo";
    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void isDocumentInAppropriateFolderTest() {
        boolean isDocumentInAppropriateFolder = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .filesMenuItemClick()
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
        boolean isDocumentSavedAndOpenedCorrectly = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .filesMenuItemClick()
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
        boolean isDocumentInTrashOnly = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .filesMenuItemClick()
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

    @AfterMethod
    public void browserClose() {
        driver.quit();
    }
}
