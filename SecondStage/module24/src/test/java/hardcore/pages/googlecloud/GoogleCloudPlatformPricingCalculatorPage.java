package hardcore.pages.googlecloud;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPlatformPricingCalculatorPage {

    private WebDriver driver;

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
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudPlatformPricingCalculatorPage setNumberOfInstance(String numberOfInstances) {
        driver.switchTo().frame(calculatorFrame);
        numberOfInstancesTextBox.sendKeys(numberOfInstances);
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectOperatingSystemSoftware(String operatingSystemSoftware) {
        selectDropdownOption(operatingSystemSoftware, operatingSystemSoftwareDropdown);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectMachineClass(String machineClass) {
        selectDropdownOption(machineClass, machineClassDropdown);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectMachineType(String machineType) {
        selectDropdownOption(machineType, machineTypeDropdown);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage checkAddGPUsCheckBox() {
        driver.switchTo().frame(calculatorFrame);
        addGPUsCheckBox.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectNumberOfGPUs(String numberOfGPUs) {
        selectDropdownOption(numberOfGPUs, numberOfGPUsDropdown);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectTypeOfGPU(String typeOfGPU) {
        selectDropdownOption(typeOfGPU, typeOfGPUDropdown);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectLocalSSD(String localSSD) {
        selectDropdownOption(localSSD, localSSDDropdown);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectDatacenterLocation(String datacenterLocation) {
        selectDropdownOption(datacenterLocation, datacenterLocationDropdown);
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage selectCommittedUsage(String committedUsage) {
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
        new WebDriverWait(driver,20)
                .until(ExpectedConditions.visibilityOfElementLocated((By.xpath(xpathLocator))));
        driver.findElement(By.xpath(xpathLocator)).click();
        driver.switchTo().defaultContent();
        return this;
    }
}
