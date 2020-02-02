package com.epam.tat.framework.driver;

import com.epam.tat.framework.exception.NotSupportedBrowserException;
import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.runner.Arguments;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static final ThreadLocal<DriverSingleton> instance = new ThreadLocal<>();
    private WebDriver driver;

    private DriverSingleton() {
    }

    public static synchronized DriverSingleton getInstance() {
        if (instance.get() == null) {
            instance.set(new DriverSingleton());
            Log.debug("New WebDriver has been created!");
        }
        return instance.get();
    }

    public WebDriver getDriver() throws NotSupportedBrowserException {
        if (driver == null) {
            switch (Arguments.instance().getBrowserType()) {
                case FIREFOX:
                    setupFirefoxDriver();
                    break;
                case CHROME:
                    setupChromeDriver();
                    break;
                default: throw new NotSupportedBrowserException(
                        "Not supported browser" + Arguments.instance().getBrowserType());
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    private void setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    private void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
    }

    public void closeDriver() {
        driver.quit();
        driver = null;
    }
}
