import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.yandexdisk.YandexDiskHomePage;

public class SimpleTestRunner {

    private final String LOGIN = "taf.alexander.gritsok";
    private final String PASSWORD = "WebDriverGo";
    private final String INVALID_LOGIN = "";
    private final String INVALID_PASSWORD = "";
    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void validCredentialsLogInShouldSucceedTest() {
        boolean isLogInSuccessful = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .isLogInSuccessful();
        Assert.assertTrue(isLogInSuccessful);
    }

    @Test
    public void invalidLoginLogInShouldFailTest() {
        boolean isLogInFailed = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(INVALID_LOGIN)
                .logInButtonClick()
                .isLogInFailed();
        Assert.assertTrue(isLogInFailed);
    }

    @Test
    public void invalidPasswordLogInShouldFailTest() {
        boolean isLogInFailed = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(INVALID_PASSWORD)
                .logInButtonClick()
                .isLogInFailed();
        Assert.assertTrue(isLogInFailed);
    }

    @Test
    public void archiveMenuItemLeadsToValidPageTest() {
        boolean isItArchivePage = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .archiveMenuItemClick()
                .isItArchivePage();

        Assert.assertTrue(isItArchivePage);
    }

    @Test
    public void filesMenuItemLeadsToValidPageTest() {
        boolean isItFilesPage = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .filesMenuItemClickAfterClickOnAnotherItem()
                .isItFilesPage();

        Assert.assertTrue(isItFilesPage);
    }

    @Test
    public void historyMenuItemLeadsToValidPageTest() {
        boolean isItHistoryPage = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .historyMenuItemClick()
                .isItHistoryPage();

        Assert.assertTrue(isItHistoryPage);
    }

    @Test
    public void photoMenuItemLeadsToValidPageTest() {
        boolean isItPhotoPage = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .photoMenuItemClick()
                .isItPhotoPage();

        Assert.assertTrue(isItPhotoPage);
    }

    @Test
    public void recentMenuItemLeadsToValidPageTest() {
        boolean isItRecentPage = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .recentMenuItemClickAfterClickOnAnotherItem()
                .isItRecentPage();

        Assert.assertTrue(isItRecentPage);
    }

    @Test
    public void sharedMenuItemLeadsToValidPageTest() {
        boolean isItSharedPage = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .sharedMenuItemClick()
                .isItSharedPage();

        Assert.assertTrue(isItSharedPage);
    }

    @Test
    public void trashMenuItemLeadsToValidPageTest() {
        boolean isItTrashPage = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage()
                .trashMenuItemClick()
                .isItTrashPage();

        Assert.assertTrue(isItTrashPage);
    }

    @Test
    public void createFolderAndVisitItTest() {
        boolean isFolderCreatedAndVisited = new YandexDiskHomePage(driver)
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
                .isFolderVisited();

        Assert.assertTrue(isFolderCreatedAndVisited);
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

    @AfterMethod
    public void browserClose() {
        //driver.quit();
    }
}
