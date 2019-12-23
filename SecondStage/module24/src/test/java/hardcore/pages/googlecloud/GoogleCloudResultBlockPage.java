package hardcore.pages.googlecloud;

import hardcore.datakeepers.KeeperOfCalculatedCost;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudResultBlockPage {

    private WebDriver driver;

    /*
     * Storage for calculated totalEstimatedCostValue so we can compare it to emailed one.
     * Giving it forward through every page.
     */
    private KeeperOfCalculatedCost keeperOfCalculatedCost;

    @FindBy(id = "myFrame")
    private WebElement calculatorFrame;

    @FindBy(xpath = "//button[@ng-click='cloudCartCtrl.showEmailForm();']")
    private WebElement emailEstimateButton;

    @FindBy (xpath = "//h2[@class='md-title']/b[contains(.,'Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    public GoogleCloudResultBlockPage(WebDriver driver) {
        this.keeperOfCalculatedCost = new KeeperOfCalculatedCost();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudEmailYourEstimatePage clickEmailEstimateButton() {
        driver.switchTo().frame(calculatorFrame);
        emailEstimateButton.click();
        driver.switchTo().defaultContent();
        return new GoogleCloudEmailYourEstimatePage(driver, keeperOfCalculatedCost);
    }

    public GoogleCloudResultBlockPage rememberTotalEstimatedCost() {
        driver.switchTo().frame(calculatorFrame);
        String totalEstimatedCostValue = totalEstimatedCost.getText().split(" ")[4];
        keeperOfCalculatedCost.setTotalCostValue(totalEstimatedCostValue);
        driver.switchTo().defaultContent();
        return this;
    }
}
