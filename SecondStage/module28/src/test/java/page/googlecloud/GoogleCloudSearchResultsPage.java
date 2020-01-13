package page.googlecloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.CommonAbstractPage;

import java.util.List;

public class GoogleCloudSearchResultsPage extends CommonAbstractPage {

    @FindBy(xpath = "//a[@class='gs-title' and contains(.,'Google Cloud Platform Pricing Calculator')]")
    private List<WebElement> requestedResult;

    public GoogleCloudSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPlatformPricingCalculatorPage clickOnGoogleCloudPlatformPricingCalculatorInResults() {
        requestedResult.get(0).click();
        return new GoogleCloudPlatformPricingCalculatorPage(driver);
    }
}
