package test;

import model.CloudPlatform;
import page.googlecloud.GoogleCloudHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import service.CloudPlatformCreator;

public class GoogleCloudPlatformPricingCalculatorTest extends TestBase {

    @Test
    public void isCalculatedCostEqualToEmailedTest() {
        CloudPlatform testPlatform = CloudPlatformCreator.withParametersFromProperties();
        boolean isCalculatedCostEqualToEmailed = new GoogleCloudHomePage(driver)
                .openPage()
                .writeSearchTerm("Google Cloud Platform Pricing Calculator")
                .search()
                .clickOnGoogleCloudPlatformPricingCalculatorInResults()
                .addPlatformToCalculator(testPlatform)
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
}
