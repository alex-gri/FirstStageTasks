package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.YandexDiskHomePage;

/**
 * Check that all main menu items works correctly and lead to correct page.
 */

public class YandexDiskMenu {

    private final String LOGIN = "taf.alexander.gritsok";
    private final String PASSWORD = "WebDriverGo";
    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
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


    @AfterMethod
    public void browserClose() {
        driver.quit();
    }
}
