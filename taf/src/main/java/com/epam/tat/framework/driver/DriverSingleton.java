package com.epam.tat.framework.driver;

import com.epam.tat.framework.exception.NotSupportedBrowserException;
import com.epam.tat.framework.runner.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static WebDriver driver;

    private DriverSingleton() {
    }

    public static WebDriver getDriver() throws NotSupportedBrowserException {
        if (driver == null) {
            switch (Parameters.instance().getBrowserType()) {
                case FIREFOX:
                    setupFirefoxDriver();
                    break;
                case CHROME:
                    setupChromeDriver();
                    break;
                default: throw new NotSupportedBrowserException(
                        "Not supported browser" + Parameters.instance().getBrowserType());
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static void setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    private static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
