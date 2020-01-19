package com.epam.tat.framework.ui;

import com.epam.tat.framework.driver.DriverSingleton;
import com.epam.tat.framework.logger.Log;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Browser implements WrapsDriver {

    private static final int WAIT_FOR_VISIBILITY_TIMEOUT_SECONDS = 10;
    private static final ThreadLocal<Browser> instance = new ThreadLocal<>();

    private WebDriver wrappedDriver;

    private Browser() {
        this.wrappedDriver = DriverSingleton.getDriver();
    }

    @Override
    public WebDriver getWrappedDriver() {
        return wrappedDriver;
    }

    public static synchronized Browser getInstance() {
        if (instance.get() == null) {
            instance.set(new Browser());
        }
        return instance.get();
    }

    public void stopBrowser() {
        try {
            if (getWrappedDriver() != null) {
                getWrappedDriver().quit();
            }
        } catch (WebDriverException e) {
            Log.error(e.getMessage());
        } finally {
            instance.set(null);
        }
    }

    public void get(String url) {
        Log.info("Moving to URL: " + url);
        wrappedDriver.get(url);
    }

    public void click(By by) {
        Log.info("Click: " + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackground(element);
        element.click();
    }

    public void type(By by, String keys) {
        Log.info("Typing: " + keys + "into: " + by);
        WebElement element = waitForVisibilityOfElement(by);
        highlightBackground(element);
        element.sendKeys(keys);
    }

    public void clear(By by) {
        wrappedDriver
                .findElement(by)
                .sendKeys(Keys.chord(Keys.LEFT_CONTROL, "a"));
    }

    public String getText(By by) {
        Log.debug("Getting text from: " + by);
        waitForVisibilityOfElement(by);
        return wrappedDriver.findElement(by).getText();
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

    public WebElement waitForVisibilityOfElement(By by, int timeout) {
        Log.debug("Waiting for visibility of element by: " + by);
        return new WebDriverWait(wrappedDriver, timeout)
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public WebElement waitForVisibilityOfElement(By by) {
        return waitForVisibilityOfElement(by, WAIT_FOR_VISIBILITY_TIMEOUT_SECONDS);
    }

    public boolean isVisible(By by) {
        try {
            waitForVisibilityOfElement(by);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    public boolean isVisibleNoWait(By by) {
        try {
            waitForVisibilityOfElement(by, 0);
            return true;
        } catch (WebDriverException e) {
            return false;
        }
    }

    private void makeScreenshot() {
        File screenCapture = ((TakesScreenshot) wrappedDriver)
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            Log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "uuuu-MM-dd_HH-mm-ss" );
        return ZonedDateTime.now().format(formatter);
    }

    private void highlightBackground(WebElement element) {
        String backgroundColor = element.getCssValue("background-color");
        JavascriptExecutor js = ((JavascriptExecutor) getWrappedDriver());
        js.executeScript("arguments[0].styles.background-color = '" + "#ffffff" + "'", element);
        makeScreenshot();
        js.executeScript("arguments[0].styles.background-color = '" + backgroundColor + "'", element);
    }
}
