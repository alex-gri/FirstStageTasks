package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.YandexDiskHomePage;

/**
 * Test login into yandex disk using credentials (positive and negative checks).
 */

public class YandexDiskLogIn {

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
    public void validCredentialsLogInTest() {
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
    public void invalidLoginLogInTest() {
        boolean isLogInFailed = new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(INVALID_LOGIN)
                .logInButtonClick()
                .isLogInFailed();
        Assert.assertTrue(isLogInFailed);
    }

    @Test
    public void invalidPasswordLogInTest() {
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

    @AfterMethod
    public void browserClose() {
        driver.quit();
    }
}
