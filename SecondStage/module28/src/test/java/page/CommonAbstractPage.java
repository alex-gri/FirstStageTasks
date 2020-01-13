package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

abstract public class CommonAbstractPage {

    protected final int WAIT_TIMEOUT_SECONDS = 30;
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger();
    protected static String keeperOfCalculatedCost;

    public CommonAbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected static ExpectedCondition<Boolean> jQueryAJAXsCompleted() {
        return driver -> (Boolean) ((JavascriptExecutor)
                driver).executeScript("return !!window.jQuery && window.jQuery.active == 0");
    }

    public String getTotalCostValue() {
        return keeperOfCalculatedCost;
    }

    protected void setTotalCostValue(String totalCostValue) {
        this.keeperOfCalculatedCost = totalCostValue;
    }
}
