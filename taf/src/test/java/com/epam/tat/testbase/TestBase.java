package com.epam.tat.testbase;

import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.AccountBuilder;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.service.AccountService;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;

public class TestBase {

    protected final String LOGIN = "taf.alexander.gritsok";
    protected final String PASSWORD = "WebDriverGo";
    private String originalWindowHandle;
    protected static WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        driver = Browser.getInstance().getWrappedDriver();
        logIn();
    }

    private void logIn() {
        Account testAccount = new AccountBuilder().login(LOGIN).password(PASSWORD).build();
        AccountService
                .logIn(testAccount)
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
        Browser.getInstance().stopBrowser();
    }
}
