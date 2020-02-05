package hardcore.tests;

import hardcore.pages.googlecloud.GoogleCloudHomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleCloudPlatformPricingCalculatorTest {

    private WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = new ChromeDriver();
    }

    @Test
    public void isCalculatedCostEqualToEmailedTest() {
        boolean isCalculatedCostEqualToEmailed = new GoogleCloudHomePage(driver)
                .openPage()
                .writeSearchTerm("Google Cloud Platform Pricing Calculator")
                .search()
                .clickOnRequestedResult()
                .setNumberOfInstance("4")
                .selectOperatingSystemSoftware("Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .selectMachineClass("Regular")
                .selectMachineType("n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .checkAddGPUsCheckBox()
                .selectNumberOfGPUs("1")
                .selectTypeOfGPU("NVIDIA Tesla V100")
                .selectLocalSSD("2x375")
                .selectDatacenterLocation("Frankfurt")
                .selectCommittedUsage("1")
                .addToEstimateButtonClick()
                .rememberTotalEstimatedCost()
                .clickEmailEstimateButton()
                .openNewTab()
                .openMinuteInboxHomePage()
                .copyEmailAddress()
                .switchToEmailYourEstimateTab()
                .setEmailAddressValue()
                .sendEmailButtonClick()
                .openEmail()
                .isEmailedTotalEstimatedMonthlyCostEqualToCalculatedValue();
        Assert.assertTrue(isCalculatedCostEqualToEmailed);
    }

    @AfterMethod
    public void browserClose() {
        driver.quit();
    }
}
