package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.YandexDiskHomePage;

/**
 * Check creation of new folder inside Файлы (use unique name for each new folder), check you can visit that folder.
 */

public class YandexDiskFolder {

    private final String LOGIN = "taf.alexander.gritsok";
    private final String PASSWORD = "WebDriverGo";
    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
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

    @AfterMethod
    public void browserClose() {
        driver.quit();
    }
}
