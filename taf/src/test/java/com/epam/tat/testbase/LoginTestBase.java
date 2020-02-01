package com.epam.tat.testbase;

import com.epam.tat.framework.ui.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class LoginTestBase {

    protected static Browser browser;

    @BeforeClass
    public void setupBrowser() {
        browser = Browser.getInstance();
    }

    @AfterMethod
    public void returnHomePage() {
        Browser.getInstance().getWrappedDriver().manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDownBrowser() {
        Browser.getInstance().stopBrowser();
    }
}
