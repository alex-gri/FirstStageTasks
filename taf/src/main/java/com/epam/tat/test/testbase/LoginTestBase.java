package com.epam.tat.test.testbase;

import com.epam.tat.framework.ui.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class LoginTestBase {

    @BeforeClass
    public void setupBrowser() {
        Browser.getInstance();
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
