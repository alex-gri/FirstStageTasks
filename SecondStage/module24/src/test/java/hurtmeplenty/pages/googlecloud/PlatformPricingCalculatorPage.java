package hurtmeplenty.pages.googlecloud;

import hurtmeplenty.datakeepers.InputKeeperOfCalculator;
import icanwin.customconditions.PageLoadingIsCompletedCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlatformPricingCalculatorPage {

    private WebDriver driver;

    // Storage for input values so they can be compared to output ones;
    private InputKeeperOfCalculator inputDataSetter;

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

    public PlatformPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        inputDataSetter = new InputKeeperOfCalculator();
        PageFactory.initElements(driver, this);
    }

    // Made this to skip part with searching of calculator (which works properly).
    public PlatformPricingCalculatorPage openPage() {
        driver.get("https://cloud.google.com/products/calculator/");
        new WebDriverWait(driver, 20)
                .until(PageLoadingIsCompletedCondition.jQueryAJAXsCompleted());
        return this;
    }

    public PlatformPricingCalculatorPage setNumberOfInstance(String numberOfInstances) {
        driver.switchTo().frame(calculatorFrame);
        numberOfInstancesTextBox.sendKeys(numberOfInstances);
        driver.switchTo().defaultContent();
        return this;
    }

    public PlatformPricingCalculatorPage selectOperatingSystemSoftware(String operatingSystemSoftware) {
        selectDropdownOption(operatingSystemSoftware, operatingSystemSoftwareDropdown);
        return this;
    }

    public PlatformPricingCalculatorPage selectMachineClass(String machineClass) {
        selectDropdownOption(machineClass, machineClassDropdown);
        inputDataSetter.setMachineClassExpected(machineClass);
        return this;
    }

    public PlatformPricingCalculatorPage selectMachineType(String machineType) {
        selectDropdownOption(machineType, machineTypeDropdown);
        inputDataSetter.setMachineTypeExpected(machineType);
        return this;
    }

    public PlatformPricingCalculatorPage checkAddGPUsCheckBox() {
        driver.switchTo().frame(calculatorFrame);
        addGPUsCheckBox.click();
        driver.switchTo().defaultContent();
        return this;
    }

    public PlatformPricingCalculatorPage selectNumberOfGPUs(String numberOfGPUs) {
        selectDropdownOption(numberOfGPUs, numberOfGPUsDropdown);
        return this;
    }

    public PlatformPricingCalculatorPage selectTypeOfGPU(String typeOfGPU) {
        selectDropdownOption(typeOfGPU, typeOfGPUDropdown);
        return this;
    }

    public PlatformPricingCalculatorPage selectLocalSSD(String localSSD) {
        selectDropdownOption(localSSD, localSSDDropdown);
        inputDataSetter.setLocalSSDExpected(localSSD);
        return this;
    }

    public PlatformPricingCalculatorPage selectDatacenterLocation(String datacenterLocation) {
        selectDropdownOption(datacenterLocation, datacenterLocationDropdown);
        inputDataSetter.setDatacenterLocationExpected(datacenterLocation);
        return this;
    }

    public PlatformPricingCalculatorPage selectCommittedUsage(String committedUsage) {
        selectDropdownOption(committedUsage, committedUsageDropdown);
        inputDataSetter.setCommittedUsageExpected(committedUsage);
        return this;
    }

    public ResultBlockPage addToEstimateButtonClick() {
        driver.switchTo().frame(calculatorFrame);
        addToEstimateButton.click();
        driver.switchTo().defaultContent();
        return new ResultBlockPage(driver, inputDataSetter);
    }

    // Builds locator to element and sets wanted option in it.
    private PlatformPricingCalculatorPage selectDropdownOption(String wantedOption, WebElement control) {
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
