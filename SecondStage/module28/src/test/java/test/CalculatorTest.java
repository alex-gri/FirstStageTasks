package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.googlecloud.GoogleCloudHomePage;

public class CalculatorTest extends TestBase {

    @Test
    public void isCalculatorPageIsFullyOpenedTest() {
        boolean isCalculatorPageFullyOpened = new GoogleCloudHomePage(driver)
                .openPage()
                .writeSearchTerm("Google Cloud Platform Pricing Calculator")
                .search()
                .clickOnGoogleCloudPlatformPricingCalculatorInResults()
                .isCalculatorPageFullyOpened();
        Assert.assertTrue(isCalculatorPageFullyOpened);
    }
}
