package page.minuteinbox;

import page.CommonAbstractPage;
import page.googlecloud.GoogleCloudEmailYourEstimatePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MinuteInboxHomePage extends CommonAbstractPage {

    /*
     * Gives us an opportunity to jump between tabs: return back to the calculator
     * and set copied email address value.
     */
    private GoogleCloudEmailYourEstimatePage previousTabPage;

    @FindBy(id = "email")
    private WebElement emailAddressField;

    private By wantedMessage = By.xpath("//span[contains(.,'Google Cloud Sales')]");
    
    public MinuteInboxHomePage(WebDriver driver, GoogleCloudEmailYourEstimatePage previousTabPage) {
        super(driver);
        this.previousTabPage = previousTabPage;
    }

    public MinuteInboxHomePage openMinuteInboxHomePage() {
        driver.get("https://www.minuteinbox.com/");
        new WebDriverWait(driver, 15).until(jQueryAJAXsCompleted());
        return this;
    }

    public GoogleCloudEmailYourEstimatePage copyEmailAddress() {
        previousTabPage.setEmailAddress(emailAddressField.getText());
        return previousTabPage;
    }

    public MinuteInboxEmailPage openEmail() {
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.presenceOfElementLocated(wantedMessage))
                .click();
        return new MinuteInboxEmailPage(driver);
    }
}
