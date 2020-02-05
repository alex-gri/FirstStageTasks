package com.epam.tat.testbase;

import com.epam.tat.Constants;
import com.epam.tat.framework.listener.SuiteListener;
import com.epam.tat.framework.listener.TestListener;
import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.builder.AccountBuilder;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.service.AccountService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;

@Listeners({TestListener.class, SuiteListener.class})
public class TestBase {

    private String originalWindowHandle;
    protected static Browser browserInstance;

    @BeforeClass
    public void setupBrowser() {
        browserInstance = Browser.getInstance();
        logIn();
    }

    private void logIn() {
        Account testAccount = new AccountBuilder().login(Constants.LOGIN).password(Constants.PASSWORD).build();
        AccountService
                .logIn(testAccount)
                .openYandexDiskFilesPage();
        originalWindowHandle = browserInstance.getWrappedDriver().getWindowHandle();
    }

    private void closeAllTabsExceptFirst() {
        for (String windowHandle : browserInstance.getWrappedDriver().getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                browserInstance.getWrappedDriver().switchTo().window(windowHandle);
                browserInstance.getWrappedDriver().close();
            }
        }
        browserInstance.getWrappedDriver().switchTo().window(originalWindowHandle);
    }

    @AfterMethod(alwaysRun = true)
    public void returnFilesPage() {
        closeAllTabsExceptFirst();
        new PassportYandexAuthorizationPage()
                .openYandexDiskFilesPage()
                .filesMenuItemClick()
                .waitForContentTitleToBe("Файлы");
    }

    @AfterClass
    public void tearDownBrowser() {
        Browser.getInstance().stopBrowser();
    }
}
