package com.epam.tat.framework.runner;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.epam.tat.framework.listener.SuiteListener;
import com.epam.tat.framework.listener.TestListener;
import com.epam.tat.framework.logger.Log;
import org.testng.TestNG;

import java.util.Collections;

public class TestRunner {

    public static void main(String[] args) {
        parseCli(args);
        Log.info("Starting app");
        createTestNG();
        Log.info("App finished");
    }

    private static void createTestNG() {
        TestNG testNG = new TestNG();
        testNG.addListener(new SuiteListener());
        testNG.addListener(new TestListener());
        testNG.setTestSuites(Collections.singletonList("./src/test/resources/testng-all.xml"));
        testNG.run();
    }

    private static void parseCli(String[] args) {
        Log.info("Parsing CLIs using JCommander");
        JCommander jCommander = new JCommander(Parameters.instance());
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            Log.error(e.getMessage());
            System.exit(1);
        }
    }
}
