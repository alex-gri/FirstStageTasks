package com.epam.tat.testbase;

import com.epam.tat.framework.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.home.YandexDiskHomePage;

public class TestBase {

    protected final String LOGIN = "taf.alexander.gritsok";
    protected final String PASSWORD = "WebDriverGo";
    private String originalWindowHandle;
    protected static WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        driver = DriverSingleton.getDriver();
        logIn();
    }

    private void logIn() {
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

    private void closeAllTabsExceptFirst() {
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
        DriverSingleton.closeDriver();
    }
}
