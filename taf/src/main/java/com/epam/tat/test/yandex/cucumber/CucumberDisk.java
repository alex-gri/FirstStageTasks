package com.epam.tat.test.yandex.cucumber;

import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.builder.AccountBuilder;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.test.Constants;
import com.epam.tat.yandex.disk.page.authorization.PassportYandexAuthorizationPage;
import com.epam.tat.yandex.disk.page.service.AccountService;
import io.cucumber.java.After;
import io.cucumber.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        strict = true,
        features = {"src/main/resources/features/document.feature"},
        glue = {"com.epam.tat.test.yandex.cucumber.disk"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
        })

public class CucumberDisk extends AbstractTestNGCucumberTests {

    private String originalWindowHandle;

    @BeforeClass
    public void setupBrowser() {
        logIn();
    }

    private void logIn() {
        Account testAccount = new AccountBuilder().login(Constants.LOGIN).password(Constants.PASSWORD).build();
        AccountService
                .logIn(testAccount)
                .openYandexDiskFilesPage();
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

    @After
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
