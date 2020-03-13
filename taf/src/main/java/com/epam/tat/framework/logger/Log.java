package com.epam.tat.framework.logger;

import com.epam.tat.framework.runner.Arguments;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Reporter;

import java.io.File;

public class Log {

    private static final Logger LOGGER = Logger.getLogger("com.epam.tat");
    private static String htmlNewLine = "</br>";

    private Log() {
        DOMConfigurator.configure(Arguments.instance().getLogger());
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void info(String message) {
        LOGGER.info(message);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void screenshot(File screenshot) {
        LOGGER.info(screenshot);
    }

    public static void report(String message) {
        Reporter.log(message + htmlNewLine);
    }

    public static void logAndReport(String message) {
        LOGGER.info(message);
        Reporter.log(message + htmlNewLine);
    }
}
