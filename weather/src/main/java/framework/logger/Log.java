package framework.logger;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class Log {
    private static final Logger LOGGER = Logger.getLogger("com.epam.tat");
    private static final String htmlNewLine = "</br>";

    private Log() {
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

    public static void report(String message) {
        Reporter.log(message + htmlNewLine);
    }

    public static void logAndReport(String message) {
        LOGGER.info(message);
        Reporter.log(message + htmlNewLine);
    }
}
