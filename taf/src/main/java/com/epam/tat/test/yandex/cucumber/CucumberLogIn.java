package com.epam.tat.test.yandex.cucumber;

import com.epam.tat.framework.ui.Browser;
import io.cucumber.testng.*;
import org.testng.annotations.AfterClass;

@CucumberOptions(
        strict = true,
        features = {"src/main/resources/features/login.feature"},
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
