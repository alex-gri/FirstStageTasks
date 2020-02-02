package com.epam.tat.framework.ui;

import com.epam.tat.framework.driver.DriverSingleton;
import com.epam.tat.framework.exception.NotSupportedBrowserException;
import com.epam.tat.framework.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Browser implements WrapsDriver {

    private static final int VISIBILITY_TIMEOUT_SECONDS = 15;
    private static final int VALUE_TO_BE_TIMEOUT_SECONDS = 30;
    private static final ThreadLocal<Browser> instance = new ThreadLocal<>();

    private WebDriver wrappedDriver;

    private Browser() {
        try {
            this.wrappedDriver = DriverSingleton.getInstance().getDriver();
        } catch (NotSupportedBrowserException e) {
            Log.error(e.getMessage());
        }
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedDriver;
    }

    public static synchronized Browser getInstance() {
        if (instance.get() == null) {
            instance.set(new Browser());
            Log.debug("NEW BROWSER IS RUNNING NOW!");
        }
        return instance.get();
    }

    public void stopBrowser() {
        try {
            if (getWrappedDriver() != null) {
                DriverSingleton.getInstance().closeDriver();
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
        } finally {
            instance.set(null);
            Log.debug("BROWSER HAS STOPPED!");
        }
    }

    public void get(String url) {
        Log.info("Moving to URL: " + url);
        wrappedDriver.get(url);
    }

    public void click(By by) {
        WebElement element = waitForVisibilityOfElementLocated(by);
        Log.info("Click: " + by);
        highlightElement(element);
        element.click();
    }

    public void clickNoWait(By by) {
        WebElement element = wrappedDriver.findElement(by);
        Log.info("Click without wait: " + by);
        highlightElement(element);
        element.click();
    }

    public void waitForAttributeToBe(By by, String attributeName, String value) {
        new WebDriverWait(wrappedDriver, VALUE_TO_BE_TIMEOUT_SECONDS)
                .until(ExpectedConditions.attributeToBe(by, attributeName, value));
    }

    public void type(By by, String keys) {
        WebElement element = waitForVisibilityOfElementLocated(by);
        Log.info("Typing: " + keys + " into: " + by);
        highlightElement(element);
        element.sendKeys(keys);
    }

    public void typeToBody(String keys) {
        Log.info("Typing into body: " + keys);
        Actions actionsBuilder = new Actions(wrappedDriver);
        actionsBuilder.sendKeys(keys).perform();
    }

    public void clear(By by) {
        new Actions(wrappedDriver)
                .keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE).build().perform();
        Log.debug("Cleared the text field :" + by);
    }

    public String getText(WebElement element) {
        waitForVisibilityOf(element);
        Log.debug("Getting text from: " + element);
        return element.getText();
    }

    public String getText(By by) {
        WebElement element = waitForVisibilityOfElementLocated(by);
        Log.debug("Getting text from: " + by);
        return element.getText();
    }

    public void swtichToFrame(By by) {
        if (by == null) {
            Log.debug("Switching to default content");
            wrappedDriver.switchTo().defaultContent();
        } else {
            Log.debug("Switching to frame: " + by);
            wrappedDriver.switchTo().frame(wrappedDriver.findElement(by));
        }
    }

    public void swtichToTab(int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(wrappedDriver.getWindowHandles());
        new WebDriverWait(wrappedDriver, VALUE_TO_BE_TIMEOUT_SECONDS).until(WebDriver::switchTo).window(tabs.get(tabIndex));
        Log.debug("Swtiched to tab: " + tabIndex);
    }

    public void closeCurrentTab() {
        Log.debug("Closing current tab");
        wrappedDriver.switchTo().window(wrappedDriver.getWindowHandle()).close();
    }

    public WebElement waitForVisibilityOfElementLocated(By by, int timeout) {
        Log.debug("Waiting for visibility of element by: " + by);
        return new WebDriverWait(wrappedDriver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForVisibilityOfElementLocated(By by) {
        return waitForVisibilityOfElementLocated(by, VISIBILITY_TIMEOUT_SECONDS);
    }

    public void waitForVisibilityOf(WebElement element) {
        new WebDriverWait(wrappedDriver, VISIBILITY_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForTextToBe(By by, String textToBe) {
        Log.debug("Waiting for: " + by + " to have text: " + textToBe);
        new WebDriverWait(wrappedDriver, VALUE_TO_BE_TIMEOUT_SECONDS)
                .until(ExpectedConditions.textToBe(by, textToBe));
    }

    public boolean isDisplayed(By by) {
        Log.info("Is element displayed check: " + by);
        try {
            return waitForVisibilityOfElementLocated(by).isDisplayed();
        } catch (WebDriverException e) {
            return false;
        }
    }

    public By xpathBuilder(String partialNameXpath, String nameToCheck) {
        return By.xpath(String.format(partialNameXpath, nameToCheck));
    }

    public void doubleClick(By by) {
        Actions action = new Actions(wrappedDriver);
        action.doubleClick(waitForVisibilityOfElementLocated(by)).build().perform();
        Log.debug("Double click: " + by);
    }

    public void makeScreenshot() {
        File screenCapture = new File("logs/screenshots/" + getCurrentTimeAsString() + ".png");
        try {
            File screenshotAsFile = ((TakesScreenshot) wrappedDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotAsFile, screenCapture);
            Log.screenshot(screenCapture);
        } catch (IOException e) {
            Log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }

    private void highlightElement(WebElement element) {
        String elementBorder = element.getCssValue("border");
        JavascriptExecutor js = ((JavascriptExecutor) getWrappedDriver());
        js.executeScript("arguments[0].style.border='3px solid red'", element);
        makeScreenshot();
        js.executeScript("arguments[0].style.border='" + elementBorder + "'", element);
    }
}
