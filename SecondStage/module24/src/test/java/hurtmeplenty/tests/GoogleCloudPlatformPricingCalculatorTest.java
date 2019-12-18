package hurtmeplenty.tests;

import hurtmeplenty.pages.GoogleCloudHomePage;
import hurtmeplenty.pages.GoogleCloudPlatformPricingCalculatorPage;
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
    public void pageTitleContainsPasteName() {
        new GoogleCloudPlatformPricingCalculatorPage(driver)
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
                .addToEstimateButtonClick();
    /* new GoogleCloudHomePage(driver)
             .openPage()
             .writeSearchTerm("Google Cloud Platform Pricing Calculator")
             .search()
             .clickOnRequestedResult()
             .setNumberOfInstance("4")
             .selectOperatingSystemSoftware("free")
             .selectMachineClass("regular")
             .selectMachineType("CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8")
             .checkAddGPUsCheckBox()
             .selectNumberOfGPUs("1")
             .selectTypeOfGPU("NVIDIA_TESLA_V100")
             .selectLocalSSD("2")
             .selectDatacenterLocation("europe-west3")
             .selectCommittedUsage("1")
             .addToEstimateButtonClick();*/
    }

    @AfterMethod
    public void browserClose() {
        //driver.quit();
        //driver = null;
    }
}
