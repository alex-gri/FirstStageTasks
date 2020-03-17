package com.epam.tat.framework.runner;

import com.epam.tat.framework.ui.Browser;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;

@CucumberOptions(
        strict = true,
        features = {"features/login.feature"},
        glue = {"com.epam.tat.test.yandex.cucumber.login"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
        })

public class CucumberLogIn extends AbstractTestNGCucumberTests {


    /*
     * Browser sets up automatically at first call of instance.
     * But we have to stop it manually here after all tests of class.
     */
    @AfterClass
    public void tearDownBrowser() {
        Browser.getInstance().stopBrowser();
    }
}