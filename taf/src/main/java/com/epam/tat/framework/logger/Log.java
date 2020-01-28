package com.epam.tat.framework.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

import java.io.File;

public class Log {

    private static final Logger LOGGER = LogManager.getLogger("com.epam.tat");

    private Log() {}

    public static void debug(String message) {
        LOGGER.debug(message);
        Reporter.log(message + "<br />");
    }

    public static void info(String message) {
        LOGGER.info(message);
        Reporter.log(message + "<br />");
    }

    public static void error(String message) {
        LOGGER.error(message);
        Reporter.log(message + "<br />");
    }

    public static void screenshot(File screenshot) {
        LOGGER.info("Screenshot captured: " + screenshot.getAbsolutePath());
        Reporter.log("<a href='"+ screenshot.getAbsolutePath() + "' target=\"blank\">" + screenshot.getName() + "</a><br />");
    }
}
