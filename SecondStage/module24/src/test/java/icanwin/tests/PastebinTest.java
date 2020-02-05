package icanwin.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import icanwin.pages.PastebinHomePage;

public class PastebinTest {

    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void addingPasteIsSuccessful() {

        boolean isPasteCreated = new PastebinHomePage(driver)
                .openPage()
                .addPasteCode("Hello from WebDriver")
                .selectPasteExpiration("10 Minutes")
                .setPasteName("helloweb")
                .createPaste();

        Assert.assertTrue(isPasteCreated);
    }

    @AfterMethod
    public void browserClose() {
        driver.quit();
        driver = null;
    }
}
