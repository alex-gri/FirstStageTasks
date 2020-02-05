package hurtmeplenty.pages.googlecloud;

import hurtmeplenty.datakeepers.InputKeeperOfCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultBlockPage {

    private WebDriver driver;
    private InputKeeperOfCalculator inputDataGetter;
    private Logger logger = LogManager.getLogger();

    @FindBy(id = "myFrame")
    private WebElement calculatorFrame;

    @FindBy (xpath = "//md-list-item[@role='listitem']/div[contains(.,'VM class')]")
    private WebElement machineClassEstimated;

    @FindBy (xpath = "//md-list-item[@role='listitem']/div[contains(.,'Instance type')]")
    private WebElement machineTypeEstimated;

    @FindBy (xpath = "//md-list-item[@role='listitem']/div[contains(.,'Region')]")
    private WebElement regionEstimated;

    @FindBy (xpath = "//md-list-item[@role='listitem']/div[contains(.,'local SSD')]")
    private WebElement localSSDEstimated;

    @FindBy (xpath = "//md-list-item[@role='listitem']/div[contains(.,'Commitment term')]")
    private WebElement committedUsageEstimated;

    @FindBy (xpath = "//h2[@class='md-title']/b[contains(.,'Total Estimated Cost')]")
    private WebElement totalEstimatedCost;

    public ResultBlockPage(WebDriver driver, InputKeeperOfCalculator inputDataSetter) {
        this.inputDataGetter = inputDataSetter;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isMachineClassValueEqualToExpected() {
        String machineClassGiven = inputDataGetter.getMachineClassExpected().toLowerCase();
        logger.info("In - " + machineClassGiven);
        return isEstimatedValueEqualToGivenValue(machineClassGiven, machineClassEstimated);
    }

    public boolean isMachineTypeValueEqualToExpected() {
        String machineTypeGiven = inputDataGetter.getMachineTypeExpected().split(" ")[0].toLowerCase();
        logger.info("In - " + machineTypeGiven);
        return isEstimatedValueEqualToGivenValue(machineTypeGiven, machineTypeEstimated);
    }

    public boolean isRegionValueEqualToExpected() {
        String regionGiven = inputDataGetter.getDatacenterLocationExpected().toLowerCase();
        logger.info("In - " + regionGiven);
        return isEstimatedValueEqualToGivenValue(regionGiven, regionEstimated);
    }

    public boolean isLocalSSDValueEqualToExpected() {
        driver.switchTo().frame(calculatorFrame);
        String localSSDGiven = inputDataGetter.getLocalSSDExpected().toLowerCase();
        String localSSDEstimatedValue = localSSDEstimated.getText().split(" ")[5].trim();
        driver.switchTo().defaultContent();
        logger.info("In - " + localSSDGiven);
        logger.info("Out - " + localSSDEstimatedValue);
        return localSSDGiven.equals(localSSDEstimatedValue);
    }

    public boolean isCommittedUsageEqualToExpected() {
        String committedUsageGiven = inputDataGetter.getCommittedUsageExpected().toLowerCase();
        logger.info("In - " + committedUsageGiven);
        return isEstimatedValueEqualToGivenValue(committedUsageGiven, committedUsageEstimated);
    }

    public boolean isTotalCostEqualToExpected() {
        driver.switchTo().frame(calculatorFrame);
        String estimatedCostExpected = "1,082.77";
        String totalEstimatedCostValue = totalEstimatedCost.getText().split(" ")[4];
        driver.switchTo().defaultContent();
        logger.info("InExpected - " + estimatedCostExpected);
        logger.info("Out - " + totalEstimatedCostValue);
        return estimatedCostExpected.equals(totalEstimatedCostValue);
    }

    private boolean isEstimatedValueEqualToGivenValue(String valueGiven, WebElement estimatedValueField) {
        driver.switchTo().frame(calculatorFrame);
        String valueEstimated = parsePureValue(estimatedValueField.getText());
        driver.switchTo().defaultContent();
        logger.info("Out - " + valueEstimated);
        return valueGiven.equals(valueEstimated);
    }

    private String parsePureValue(String fullValue) {
        return fullValue.split(":")[1].trim().toLowerCase().split(" ")[0];
    }
}
