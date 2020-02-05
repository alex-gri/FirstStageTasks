package hurtmeplenty.pages.googlecloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;

    @FindBy(xpath = "//a[@class='gs-title' and contains(.,'Google Cloud Platform Pricing Calculator')]")
    private List<WebElement> requestedResult;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PlatformPricingCalculatorPage clickOnRequestedResult() {
        requestedResult.get(0).click();
        return new PlatformPricingCalculatorPage(driver);
    }
}
