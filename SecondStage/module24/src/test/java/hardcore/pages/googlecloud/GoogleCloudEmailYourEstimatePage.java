package hardcore.pages.googlecloud;

import hardcore.datakeepers.KeeperOfCalculatedCost;
import hardcore.pages.tenminutemail.MinuteInboxHomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

public class GoogleCloudEmailYourEstimatePage {

    private WebDriver driver;

    // Field to store copied temporary email address.
    private String emailAddress;

    // Using this field we can return back to mail tab to wait an email.
    private MinuteInboxHomePage minuteInboxHomePage;

    private KeeperOfCalculatedCost keeperOfCalculatedCost;

    @FindBy(id = "myFrame")
    private WebElement calculatorFrame;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public GoogleCloudEmailYourEstimatePage(WebDriver driver, KeeperOfCalculatedCost keeperOfCalculatedCost) {
        this.driver = driver;
        this.keeperOfCalculatedCost = keeperOfCalculatedCost;
        PageFactory.initElements(driver, this);
    }

    public void setEmailField(WebElement emailField) {
        this.emailField = emailField;
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

    // Using getter because we already gave forward whole object including this field.
    public KeeperOfCalculatedCost getKeeperOfCalculatedCost() {
        return keeperOfCalculatedCost;
    }

    private void switchTabByIndex(int i) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(i));
    }
}
