package com.epam.tat.testbase;

import com.epam.tat.framework.ui.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class LoginTestBase {

    protected final String LOGIN = "taf.alexander.gritsok";
    protected final String PASSWORD = "WebDriverGo";
    protected final String INCORRECT_VALUE = "qazxcdews11111111111";
    protected static WebDriver driver;

    @BeforeClass
    public void setupBrowser() {
        driver = Browser.getInstance().getWrappedDriver();
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
