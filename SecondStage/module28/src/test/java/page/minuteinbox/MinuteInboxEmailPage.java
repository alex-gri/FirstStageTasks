package page.minuteinbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import page.CommonAbstractPage;

public class MinuteInboxEmailPage extends CommonAbstractPage {

    @FindBy (xpath = "//h3[contains(.,'USD')]")
    private WebElement totalEstimatedMonthlyCost;

    public MinuteInboxEmailPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailedTotalEstimatedMonthlyCostEqualToCalculatedValue() {
        driver.switchTo().frame("iframeMail");
        String emailedTotalEstimatedMonthlyCost = totalEstimatedMonthlyCost.getText().split(" ")[1].trim();
        String calculatedTotalEstimatedMonthlyCost = getTotalCostValue();
        logger.info("calculated = " + calculatedTotalEstimatedMonthlyCost);
        logger.info("emailed = " + emailedTotalEstimatedMonthlyCost);
        driver.switchTo().defaultContent();
        return calculatedTotalEstimatedMonthlyCost.equals(emailedTotalEstimatedMonthlyCost);
    }
}
