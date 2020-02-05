package page.googlecloud;

import page.CommonAbstractPage;
import page.minuteinbox.MinuteInboxHomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class GoogleCloudEmailYourEstimatePage extends CommonAbstractPage {

    // Field to store copied temporary email address.
    private String emailAddress;

    // Using this field we can return back to mail tab to wait an email.
    private MinuteInboxHomePage minuteInboxHomePage;

    @FindBy (id = "myFrame")
    private WebElement calculatorFrame;

    @FindBy (xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy (xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public GoogleCloudEmailYourEstimatePage(WebDriver driver) {
        super(driver);
    }

    public MinuteInboxHomePage openNewTab() {
        ((JavascriptExecutor)driver).executeScript("window.open()");
        switchTabByIndex(1);
        minuteInboxHomePage = new MinuteInboxHomePage(driver, this);
        return minuteInboxHomePage;
    }

    public GoogleCloudEmailYourEstimatePage switchToEmailYourEstimateTab() {
        switchTabByIndex(0);
        return this;
    }

    public GoogleCloudEmailYourEstimatePage setEmailAddressValue() {
        driver.switchTo().frame(calculatorFrame);
        emailField.sendKeys(emailAddress);
        driver.switchTo().defaultContent();
        return this;
    }

    public MinuteInboxHomePage sendEmailButtonClick() {
        driver.switchTo().frame(calculatorFrame);
        sendEmailButton.click();
        driver.switchTo().defaultContent();
        switchTabByIndex(1);
        return minuteInboxHomePage;
    }

    // Using setter we copy temporary email address from another tab.
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private void switchTabByIndex(int i) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(i));
    }
}
