package page.googlecloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.CommonAbstractPage;


public class GoogleCloudResultBlockPage extends CommonAbstractPage {

    @FindBy(id = "myFrame")
    private WebElement calculatorFrame;

    @FindBy(xpath = "//button[@ng-click='cloudCartCtrl.showEmailForm();']")
    private WebElement emailEstimateButton;

    @FindBy (xpath = "//h2[@class='md-title']/b[contains(.,'Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    public GoogleCloudResultBlockPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudEmailYourEstimatePage clickEmailEstimateButton() {
        driver.switchTo().frame(calculatorFrame);
        emailEstimateButton.click();
        driver.switchTo().defaultContent();
        return new GoogleCloudEmailYourEstimatePage(driver);
    }

    public GoogleCloudResultBlockPage rememberTotalEstimatedCost() {
        driver.switchTo().frame(calculatorFrame);
        String totalEstimatedCostValue = totalEstimatedCost.getText().split(" ")[4];
        setTotalCostValue(totalEstimatedCostValue);
        driver.switchTo().defaultContent();
        return this;
    }
}
