package com.epam.tat.framework.runner;

import com.epam.tat.framework.model.Account;
import com.epam.tat.framework.model.builder.AccountBuilder;
import com.epam.tat.framework.ui.Browser;
import com.epam.tat.test.Constants;
import com.epam.tat.yandex.disk.page.service.AccountService;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@CucumberOptions(
        strict = true,
        features = {"features/files.feature"},
        glue = {"com.epam.tat.test.yandex.cucumber.disk", },
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
        })

public class CucumberDisk extends AbstractTestNGCucumberTests {

    private static ThreadLocal<String> instance = new ThreadLocal<>();

    @BeforeClass
    public void setupBrowser() {
        logIn();
    }

    private void logIn() {
        Account testAccount = new AccountBuilder().login(Constants.LOGIN).password(Constants.PASSWORD).build();
        AccountService
                .logIn(testAccount)
                .openYandexDiskTrashPage();
        instance.set(Browser.getInstance().getWrappedDriver().getWindowHandle());
    }

    public static void closeAllTabsExceptFirst() {
        for (String windowHandle : Browser.getInstance().getWrappedDriver().getWindowHandles()) {
            if (!windowHandle.equals(instance.get())) {
                Browser.getInstance().getWrappedDriver().switchTo().window(windowHandle);
                Browser.getInstance().getWrappedDriver().close();
            }
        }
        Browser.getInstance().getWrappedDriver().switchTo().window(instance.get());
    }

    @AfterClass
    public void tearDownBrowser() {
        instance.remove();
        Browser.getInstance().stopBrowser();
    }
}
