package com.epam.tat.framework.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {

    private static final Logger LOG = LogManager.getLogger("com.epam.tat");

    public static void info(Object message) {
        LOG.info(message);
    }

    public static void error(Object message) {
        LOG.error(message);
    }

    public static void debug(Object message) {
        LOG.debug(message);
    }
}
