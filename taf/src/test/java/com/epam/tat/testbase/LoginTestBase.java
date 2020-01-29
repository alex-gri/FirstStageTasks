package com.epam.tat.testbase;

import com.epam.tat.framework.listener.SuiteListener;
import com.epam.tat.framework.listener.TestListener;
import com.epam.tat.framework.ui.Browser;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class, SuiteListener.class})
public class LoginTestBase {

    protected final String LOGIN = "taf.alexander.gritsok";
    protected final String PASSWORD = "WebDriverGo";
    protected final String INCORRECT_VALUE = "qazxcdews11111111111";
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
