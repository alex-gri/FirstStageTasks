package com.epam.tat.framework.runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.epam.tat.framework.listener.SuiteListener;
import com.epam.tat.framework.listener.TestListener;
import com.epam.tat.framework.logger.Log;
import com.epam.tat.framework.util.StringUtils;
import org.testng.TestNG;

public class TestRunner {

    public static void main(String[] args) {
        parseCli(args);
        Log.logAndReport("Starting app with parameters: " + StringUtils.removeCommas(args));
        createTestNG().run();
        Log.logAndReport("App finished");
    }

    private static TestNG createTestNG() {
        TestNG testNG = new TestNG();
        testNG.addListener(new SuiteListener());
        testNG.addListener(new TestListener());
        testNG.setTestSuites(Arguments.instance().getSuites());
        testNG.setParallel(Arguments.instance().getParallel());
        testNG.setThreadCount(Arguments.instance().getThreadCount());
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
