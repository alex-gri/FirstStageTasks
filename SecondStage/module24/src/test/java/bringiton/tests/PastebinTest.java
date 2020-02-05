package bringiton.tests;

import bringiton.pages.PastebinHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PastebinTest {

    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void pageTitleContainsPasteName() {
        boolean pageTitleContainsPasteName = new PastebinHomePage(driver)
                .openPage()
                .addPasteCode()
                .selectSyntaxHighlighting("Bash")
                .selectPasteExpiration("10 Minutes")
                .setPasteName("how to gain dominance among developers")
                .createPaste()
                .pageTitleContainsPasteName();

        Assert.assertTrue(pageTitleContainsPasteName);
    }

    @Test
    public void syntaxHighlightingIsBash() {
        boolean syntaxHighlightingIsBash = new PastebinHomePage(driver)
                .openPage()
                .addPasteCode()
                .selectSyntaxHighlighting("Bash")
                .selectPasteExpiration("10 Minutes")
                .setPasteName("how to gain dominance among developers")
                .createPaste()
                .syntaxHighlightingIsBash();

        Assert.assertTrue(syntaxHighlightingIsBash);
    }

    @Test
    public void pasteCodeEqualsToSource() {
        boolean pasteCodeEqualsToSource = new PastebinHomePage(driver)
                .openPage()
                .addPasteCode()
                .selectSyntaxHighlighting("Bash")
                .selectPasteExpiration("10 Minutes")
                .setPasteName("how to gain dominance among developers")
                .createPaste()
                .pasteCodeEqualsToSource(); // Reading code from property file.

        Assert.assertTrue(pasteCodeEqualsToSource);
    }

    @AfterMethod
    public void browserClose() {
        driver.quit();
        driver = null;
    }
}
