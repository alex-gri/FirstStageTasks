package test;

import framework.cli.Arguments;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import framework.logger.Log;
import framework.listener.SuiteListener;
import org.testng.TestNG;
import framework.util.StringUtil;
import framework.listener.TestListener;

public class TestRunner {

    public static void main(String[] args) {
        parseCli(args);
        Log.logAndReport("Starting app with parameters: " + StringUtil.removeCommas(args));
        createTestNG().run();
        Log.logAndReport("App finished");
    }

    private static TestNG createTestNG() {
        TestNG testNG = new TestNG();
        testNG.addListener(new SuiteListener());
        testNG.addListener(new TestListener());
        testNG.setTestSuites(Arguments.instance().getSuites());
        return testNG;
    }

    private static void parseCli(String[] args) {
        Log.info("Parsing CLIs using JCommander");
        JCommander jCommander = new JCommander();
        try {
            jCommander.newBuilder()
                    .args(args)
                    .addObject(Arguments.instance())
                    .build()
                    .parse();
        } catch (ParameterException e) {
            Log.error(e.getMessage());
            jCommander.usage();
            System.exit(1);
        }
    }
}
