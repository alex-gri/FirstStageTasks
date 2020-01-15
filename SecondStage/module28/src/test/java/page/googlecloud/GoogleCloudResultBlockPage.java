package page.googlecloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.CommonAbstractPage;


public class GoogleCloudResultBlockPage extends CommonAbstractPage {

    @FindBy (id = "myFrame")
    private WebElement calculatorFrame;

    private By emailEstimateButton = By.xpath("//button[@ng-click='cloudCartCtrl.showEmailForm();']");
    private By emailFormXpath = By.xpath("//md-toolbar[@class='cpc-toolbar md-default-theme']");

    @FindBy (xpath = "//h2[@class='md-title']/b[contains(.,'Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    public GoogleCloudResultBlockPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudEmailYourEstimatePage clickEmailEstimateButton() {
        driver.switchTo().frame(calculatorFrame);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(emailEstimateButton))
                .click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(emailFormXpath));
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
