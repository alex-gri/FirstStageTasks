package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.YandexDiskHomePage;

/**
 * Test you can empty trash. Check that document removed completely.
 */

public class YandexDiskTrash {

    private final String LOGIN = "taf.alexander.gritsok";
    private final String PASSWORD = "WebDriverGo";
    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void isFolderRemovedFromTrashAfterClean() {
        boolean isFolderRemovedFromTrashAfterClean = new YandexDiskHomePage(driver)
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
                .deleteButtonClick()
                .trashMenuItemClick()
                .emptyTrashButtonClick()
                .confirmEmptyTrashButtonClick()
                .isTrashEmpty();

        Assert.assertTrue(isFolderRemovedFromTrashAfterClean);
    }

    @AfterMethod
    public void browserClose() {
        driver.quit();
    }
}
