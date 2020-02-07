package com.epam.tat.framework.runner;

import com.epam.tat.framework.ui.Browser;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

@CucumberOptions(
        strict = true,
        features = {"src/main/resources/features"},
        glue = {"com.epam.tat.test.yandex.cucumber"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
        })

public class CucumberLogIn {

        private TestNGCucumberRunner testNGCucumberRunner;

        @BeforeClass
        public void setupBrowser() {
                Browser.getInstance();
                testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
        }

        @AfterMethod
        public void returnHomePage() {
                Browser.getInstance().getWrappedDriver().manage().deleteAllCookies();
        }

        @AfterClass
        public void tearDownBrowser() {
                Browser.getInstance().stopBrowser();
                testNGCucumberRunner.finish();
        }

        @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
        public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) throws Throwable {
                // the 'featureWrapper' parameter solely exists to display the feature file in a test report
                testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
        }

        @DataProvider
        public Object[][] scenarios() {
                return testNGCucumberRunner.provideScenarios();
        }
}
