package com.epam.tat.framework.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

import java.io.File;

public class Log {

    private static final Logger LOG = LogManager.getLogger("com.epam.tat");

    public static void debug(String message) {
        LOG.debug(message);
        Reporter.log(message, 1);
    }

    public static void info(String message) {
        LOG.info(message);
        Reporter.log(message, 2);
    }

    public static void error(String message) {
        LOG.error(message);
        Reporter.log(message, 3);
    }

    public static void screenshot(File screenshot) {
        String logMessage = "Screenshot captured: " + screenshot.getAbsolutePath();
        LOG.info(logMessage);
        Reporter.log(logMessage, 2);
        Reporter.log("<a href='"+ screenshot.getAbsolutePath() + "'>" + screenshot.getName() +"</a>");
    }
}
