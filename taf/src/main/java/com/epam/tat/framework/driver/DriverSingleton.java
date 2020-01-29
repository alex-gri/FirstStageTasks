package com.epam.tat.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
//            switch (System.getProperty("browser")) {
//                case "firefox": {
//                    WebDriverManager.firefoxdriver().setup();
//                    driver = new FirefoxDriver();
//                }
//                default: {
//                    WebDriverManager.chromedriver().setup();
//                    driver = new ChromeDriver();
//                }
//            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
