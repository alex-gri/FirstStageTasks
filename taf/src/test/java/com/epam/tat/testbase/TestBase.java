package com.epam.tat.testbase;

import com.epam.tat.framework.listener.TestListener;
import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.AccountBuilder;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.service.AccountService;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class TestBase {

    protected final String LOGIN = "taf.alexander.gritsok";
    protected final String PASSWORD = "WebDriverGo";
    private String originalWindowHandle;
    protected static Browser browser;

    @BeforeClass
    public void setupBrowser() {
        browser = Browser.getInstance();
        logIn();
    }

    private void logIn() {
        Account testAccount = new AccountBuilder().login(LOGIN).password(PASSWORD).build();
        AccountService
                .logIn(testAccount)
                .openYandexDiskFilesPage();
        originalWindowHandle = browser.getWrappedDriver().getWindowHandle();
    }

    private void closeAllTabsExceptFirst() {
        for (String windowHandle : browser.getWrappedDriver().getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                browser.getWrappedDriver().switchTo().window(windowHandle);
                browser.getWrappedDriver().close();
            }
        }
        browser.getWrappedDriver().switchTo().window(originalWindowHandle);
    }

    @AfterMethod (alwaysRun = true)
    public void returnFilesPage() {
        closeAllTabsExceptFirst();
        new PassportYandexAuthorizationPage()
                .openYandexDiskFilesPage()
                .filesMenuItemClick();
    }

    @AfterClass
    public void tearDownBrowser() {
        Browser.getInstance().stopBrowser();
    }
}
