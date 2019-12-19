package hurtmeplenty.tests;

import hurtmeplenty.pages.googlecloud.PlatformPricingCalculatorPage;
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
    public void isMachineClassValueEqualToExpected() {
        boolean isMachineClassValueEqualToExpected = new PlatformPricingCalculatorPage(driver)
                .openPage()
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
                .isMachineClassValueEqualToExpected();
        Assert.assertTrue(isMachineClassValueEqualToExpected);
//     new GoogleCloudHomePage(driver)
//             .openPage()
//             .writeSearchTerm("Google Cloud Platform Pricing Calculator")
//             .search()
//             .clickOnRequestedResult()
    }

    @Test
    public void isMachineTypeValueEqualToExpected() {
        boolean isMachineTypeValueEqualToExpected = new PlatformPricingCalculatorPage(driver)
                .openPage()
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
                .isMachineTypeValueEqualToExpected();
        Assert.assertTrue(isMachineTypeValueEqualToExpected);
    }

    @Test
    public void isRegionValueEqualToExpected() {
        boolean isRegionValueEqualToExpected = new PlatformPricingCalculatorPage(driver)
                .openPage()
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
                .isRegionValueEqualToExpected();
        Assert.assertTrue(isRegionValueEqualToExpected);
    }

    @Test
    public void isLocalSSDValueEqualToExpected() {
        boolean isLocalSSDValueEqualToExpected = new PlatformPricingCalculatorPage(driver)
                .openPage()
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
                .isLocalSSDValueEqualToExpected();
        Assert.assertTrue(isLocalSSDValueEqualToExpected);
    }

    @Test
    public void isCommittedUsageEqualToExpected() {
        boolean isCommittedUsageEqualToExpected = new PlatformPricingCalculatorPage(driver)
                .openPage()
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
                .isCommittedUsageEqualToExpected();
        Assert.assertTrue(isCommittedUsageEqualToExpected);
    }

    @Test
    public void isTotalCostEqualToExpected() {
        boolean isTotalCostEqualToExpected = new PlatformPricingCalculatorPage(driver)
                .openPage()
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
                .isTotalCostEqualToExpected();
        Assert.assertTrue(isTotalCostEqualToExpected);
    }

    @AfterMethod
    public void browserClose() {
        driver.quit();
        driver = null;
    }
}
