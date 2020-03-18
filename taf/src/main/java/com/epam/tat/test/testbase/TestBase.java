package com.epam.tat.test.testbase;

import com.epam.tat.test.Constants;
import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.builder.AccountBuilder;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.yandex.disk.page.service.AccountService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;

public class TestBase {

    private String originalWindowHandle;

    @BeforeClass
    public void setupBrowser() {
        logIn();
    }

    private void logIn() {
        Account testAccount = new AccountBuilder().login(Constants.LOGIN).password(Constants.PASSWORD).build();
        AccountService
                .logIn(testAccount)
                .openYandexDiskTrashPage();
        originalWindowHandle = Browser.getInstance().getWrappedDriver().getWindowHandle();
    }

    private void closeAllTabsExceptFirst() {
        for (String windowHandle : Browser.getInstance().getWrappedDriver().getWindowHandles()) {
            if (!windowHandle.equals(originalWindowHandle)) {
                Browser.getInstance().getWrappedDriver().switchTo().window(windowHandle);
                Browser.getInstance().getWrappedDriver().close();
            }
        }
        Browser.getInstance().getWrappedDriver().switchTo().window(originalWindowHandle);
    }

    @AfterMethod(alwaysRun = true)
    public void returnFilesPage() {
        closeAllTabsExceptFirst();
        new PassportYandexAuthorizationPage()
                .openYandexDiskTrashPage()
                .filesMenuItemClick()
                .waitForContentTitleToBe("Файлы");
    }

    @AfterClass
    public void tearDownBrowser() {
        Browser.getInstance().stopBrowser();
    }
}
