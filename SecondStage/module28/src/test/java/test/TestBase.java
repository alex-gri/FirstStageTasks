package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.TestListener;

@Listeners ({TestListener.class})
public class TestBase {

    protected WebDriver driver;

    @BeforeMethod
    public void setUpBrowser() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod (alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
