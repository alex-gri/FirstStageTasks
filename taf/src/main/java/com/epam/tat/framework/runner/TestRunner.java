package com.epam.tat.framework.runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.epam.tat.framework.listener.SuiteListener;
import com.epam.tat.framework.listener.TestListener;
import com.epam.tat.framework.logger.Log;
import org.testng.TestNG;

public class TestRunner {

    public static void main(String[] args) {
        parseCli(args);
        Log.info("Starting app");
        createTestNG().run();
        Log.info("App finished");
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
            System.out.println(Arguments.instance().getSuites());
        } catch (ParameterException e) {
            Log.error(e.getMessage());
            jCommander.usage();
            System.exit(1);
        }
    }
}
