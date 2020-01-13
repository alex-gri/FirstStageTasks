package page.googlecloud;

import model.CloudPlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.CommonAbstractPage;

public class GoogleCloudPlatformPricingCalculatorPage extends CommonAbstractPage {

    @FindBy(id = "myFrame")
    private WebElement calculatorFrame;

    @FindBy(xpath = "//md-tab-item/div[@title='Compute Engine']")
    private WebElement computeEngineTab;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstancesTextBox;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']")
    private WebElement operatingSystemSoftwareDropdown;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement machineClassDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement machineTypeDropdown;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGPUsCheckBox;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUsDropdown;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement typeOfGPUDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD']")
    private WebElement localSSDDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
    private WebElement datacenterLocationDropdown;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement committedUsageDropdown;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate' and @ng-disabled='ComputeEngineForm.$invalid || !listingCtrl.isGceAvailable']")
    private WebElement addToEstimateButton;

    public GoogleCloudPlatformPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCalculatorPageFullyOpened() {
        return new WebDriverWait(driver, 20)
                .until(jQueryAJAXsCompleted());
    }

    public GoogleCloudPlatformPricingCalculatorPage addPlatformToCalculator(CloudPlatform platform) {
        setNumberOfInstance(platform.getNumberOfInstance());
        selectOperatingSystemSoftware(platform.getOperatingSystemSoftware());
        selectMachineClass(platform.getMachineClass());
        selectMachineType(platform.getMachineType());
        checkAddGPUsCheckBox();
        selectNumberOfGPUs(platform.getNumberOfGPU());
        selectTypeOfGPU(platform.getTypeOfGPU());
        selectLocalSSD(platform.getLocalSSD());
        selectDatacenterLocation(platform.getDatacenterLocation());
        selectCommittedUsage(platform.getCommittedUsage());
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage setNumberOfInstance(String numberOfInstances) {
        driver.switchTo().frame(calculatorFrame);
        numberOfInstancesTextBox.sendKeys(numberOfInstances);
        driver.switchTo().defaultContent();
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage selectOperatingSystemSoftware(String operatingSystemSoftware) {
        selectDropdownOption(operatingSystemSoftware, operatingSystemSoftwareDropdown);
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage selectMachineClass(String machineClass) {
        selectDropdownOption(machineClass, machineClassDropdown);
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage selectMachineType(String machineType) {
        selectDropdownOption(machineType, machineTypeDropdown);
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage checkAddGPUsCheckBox() {
        driver.switchTo().frame(calculatorFrame);
        addGPUsCheckBox.click();
        driver.switchTo().defaultContent();
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage selectNumberOfGPUs(String numberOfGPUs) {
        selectDropdownOption(numberOfGPUs, numberOfGPUsDropdown);
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage selectTypeOfGPU(String typeOfGPU) {
        selectDropdownOption(typeOfGPU, typeOfGPUDropdown);
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage selectLocalSSD(String localSSD) {
        selectDropdownOption(localSSD, localSSDDropdown);
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage selectDatacenterLocation(String datacenterLocation) {
        selectDropdownOption(datacenterLocation, datacenterLocationDropdown);
        return this;
    }

    private GoogleCloudPlatformPricingCalculatorPage selectCommittedUsage(String committedUsage) {
        selectDropdownOption(committedUsage, committedUsageDropdown);
        return this;
    }

    public GoogleCloudResultBlockPage addToEstimateButtonClick() {
        driver.switchTo().frame(calculatorFrame);
        addToEstimateButton.click();
        driver.switchTo().defaultContent();
        return new GoogleCloudResultBlockPage(driver);
    }

    // Builds locator to element and sets wanted option in it.
    private GoogleCloudPlatformPricingCalculatorPage selectDropdownOption(String wantedOption, WebElement control) {
        driver.switchTo().frame(calculatorFrame);
        control.click();
        String xpathLocator = String.format("//div[@class='md-select-menu-container md-active md-clickable']" +
                "//md-option[contains(.,'%s')]", wantedOption);
        new WebDriverWait(driver, 20)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)))
                .click();
        driver.switchTo().defaultContent();
        return this;
    }
}
