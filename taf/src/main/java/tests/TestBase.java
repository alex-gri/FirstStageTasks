package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.PassportYandexAuthorizationPage;
import pages.YandexDiskHomePage;

public class TestBase {

    private final String LOGIN = "taf.alexander.gritsok";
    private final String PASSWORD = "WebDriverGo";
    private String originalWindowHandle;
    protected WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        driver = new ChromeDriver();
        logIn();
    }

    public void logIn() {
        new YandexDiskHomePage(driver)
                .openYandexDiskHomePage()
                .logInButtonClick()
                .setLogin(LOGIN)
                .logInButtonClick()
                .setPassword(PASSWORD)
                .logInButtonClick()
                .openYandexDiskFilesPage();

        originalWindowHandle = driver.getWindowHandle();
    }

    public void closeAllTabsExceptFirst() {
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(originalWindowHandle);
    }

    @AfterMethod (alwaysRun = true)
    public void returnFilesPage() {
        closeAllTabsExceptFirst();
        new PassportYandexAuthorizationPage(driver)
                .openYandexDiskFilesPage()
                .filesMenuItemClick();
    }

    @AfterClass
    public void tearDownBrowser() {
        driver.quit();
    }
}
