package com.epam.tat.framework.driver;

import com.epam.tat.framework.exception.NotSupportedBrowserException;
import com.epam.tat.framework.runner.Arguments;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static DriverSingleton instance = new DriverSingleton();
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static synchronized DriverSingleton getInstance() {
        return instance;
    }

    public static WebDriver createDriverInstance() throws NotSupportedBrowserException {
        WebDriver driver = null;

        switch (Arguments.instance().getBrowserType()) {
            case FIREFOX:
                setupFirefoxDriver();
                break;
            case CHROME:
                driver = setupChromeDriver();
                break;
            default: throw new NotSupportedBrowserException(
                    "Not supported browser" + Arguments.instance().getBrowserType());
        }
        return driver;
    }

    public void setDriver(WebDriver newdriver) {
        driver.set(newdriver);
    }

    public  WebDriver getDriver() {
        return driver.get();
    }

    public  void removeDriver() {
        driver.get().quit();
        driver.remove();
    }

    private static WebDriver setupFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private static WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors", "start-maximized");
        return new ChromeDriver(options);
    }
}
