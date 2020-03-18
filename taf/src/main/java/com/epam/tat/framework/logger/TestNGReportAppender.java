package com.epam.tat.framework.logger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

import java.io.File;

public class TestNGReportAppender extends AppenderSkeleton {

    /*
     * If File is given to log, appender transforms it to the link in TestNG report.
     */
    @Override
    protected void append(final LoggingEvent event) {
        if (event.getMessage() instanceof File) {
            Reporter.log(screenshotToString(event));
        }
    }

    private String screenshotToString(final LoggingEvent event) {
        StringBuilder logMessage = new StringBuilder();
        File screenshot = (File) event.getMessage();
        logMessage.append("<a href='").append(screenshot.getAbsolutePath()).append("' target=\"blank\">")
                  .append(screenshot.getName()).append("</a>").append("</br>");
        return logMessage.toString();
    }

    @Override
    public void close()
    {
    }

    @Override
    public boolean requiresLayout()
    {
        return false;
    }
}
