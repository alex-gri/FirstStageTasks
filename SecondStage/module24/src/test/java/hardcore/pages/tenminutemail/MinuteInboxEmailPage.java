package hardcore.pages.tenminutemail;

import hardcore.datakeepers.KeeperOfCalculatedCost;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinuteInboxEmailPage {

    private WebDriver driver;
    private Logger logger = LogManager.getLogger();

    // Storage for calculated totalEstimatedCostValue so we can compare it to emailed one.
    private KeeperOfCalculatedCost keeperOfCalculatedCost;

    @FindBy (xpath = "//h3[contains(.,'USD')]")
    private WebElement totalEstimatedMonthlyCost;

    public MinuteInboxEmailPage(WebDriver driver, KeeperOfCalculatedCost keeperOfCalculatedCost) {
        this.driver = driver;
        this.keeperOfCalculatedCost = keeperOfCalculatedCost;
        PageFactory.initElements(driver, this);
    }

    public boolean isEmailedTotalEstimatedMonthlyCostEqualToCalculatedValue() {
        driver.switchTo().frame("iframeMail");
        String emailedTotalEstimatedMonthlyCost = totalEstimatedMonthlyCost.getText().split(" ")[1].trim();
        String calculatedTotalEstimatedMonthlyCost = keeperOfCalculatedCost.getTotalCostValue();
        logger.info("calculated = " + calculatedTotalEstimatedMonthlyCost);
        logger.info("emailed = " + emailedTotalEstimatedMonthlyCost);
        driver.switchTo().defaultContent();
        return calculatedTotalEstimatedMonthlyCost.equals(emailedTotalEstimatedMonthlyCost);
    }
}
