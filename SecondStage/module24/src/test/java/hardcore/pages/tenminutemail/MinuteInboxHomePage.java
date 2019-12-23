package hardcore.pages.tenminutemail;

import hardcore.customconditions.PageLoadingIsCompletedCondition;
import hardcore.datakeepers.KeeperOfCalculatedCost;
import hardcore.pages.googlecloud.GoogleCloudEmailYourEstimatePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MinuteInboxHomePage {

    private WebDriver driver;

    /*
     * Gives us an opportunity to jump between tabs: return back to the calculator
     * and set copied email address value.
     */
    private GoogleCloudEmailYourEstimatePage previousTabPage;

    /*
     * Storage for calculated totalEstimatedCostValue so we can compare it to emailed one.
     * Giving it forward through every page.
     */
    private KeeperOfCalculatedCost keeperOfCalculatedCost;

    @FindBy(id = "email")
    private WebElement emailAddressField;

    public MinuteInboxHomePage(WebDriver driver, GoogleCloudEmailYourEstimatePage previousTabPage) {
        this.driver = driver;
        this.previousTabPage = previousTabPage;
        PageFactory.initElements(driver, this);
    }

    public MinuteInboxHomePage openMinuteInboxHomePage() {
        driver.get("https://www.minuteinbox.com/");
        new WebDriverWait(driver, 15).until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
        return this;
    }

    public GoogleCloudEmailYourEstimatePage copyEmailAddress() {
        previousTabPage.setEmailAddress(emailAddressField.getText());
        keeperOfCalculatedCost = previousTabPage.getKeeperOfCalculatedCost();
        return previousTabPage;
    }

    public MinuteInboxEmailPage openEmail() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                          "//td[@class='from' and contains(.,'Sales')]")));
        driver.findElement(By.xpath("//td[@class='from' and contains(.,'Sales')]")).click();
        return new MinuteInboxEmailPage(driver, keeperOfCalculatedCost);
    }
}
