package bringiton.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

public abstract class AbstractPage {

    protected final int WAIT_TIMEOUT_SECONDS = 30;
    protected WebDriver driver;
    protected Logger logger = Logger.getLogger(PastebinHomePage.class);

    protected abstract String getPasteCodeFromProperties();

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
