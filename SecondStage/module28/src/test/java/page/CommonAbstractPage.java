package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public abstract class CommonAbstractPage {

    protected static final int WAIT_TIMEOUT_SECONDS = 30;
    protected WebDriver driver;
    protected Logger logger = LogManager.getLogger();
    protected static String keeperOfCalculatedCost;

    public CommonAbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForPageLoadIsComplete() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(webDriver ->
                ((JavascriptExecutor) Objects.requireNonNull(driver))
                        .executeScript( "return document.readyState")
                        .equals("complete"));
    }

    public String getTotalCostValue() {
        return keeperOfCalculatedCost;
    }

    protected void setTotalCostValue(String totalCostValue) {
        this.keeperOfCalculatedCost = totalCostValue;
    }
}
