package com.epam.tat.framework.logger;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

import java.io.File;

public class TestNGReportAppender extends AppenderSkeleton {

    @Override
    protected void append(final LoggingEvent event) {
        if (!(event.getMessage() instanceof File)) {
            Reporter.log(eventToString(event));
        } else {
            Reporter.log(screenshotToString(event));
        }
    }

    private String screenshotToString(final LoggingEvent event) {
        StringBuilder result = new StringBuilder(layout.format(event));
        File screenshot = (File) event.getMessage();
        result.append("<a href='").append(screenshot.getAbsolutePath()).append("' target=\"blank\">")
                .append(screenshot.getName()).append("</a>").append(Layout.LINE_SEP).append("</br>");
        return result.toString();
    }

    private String eventToString(final LoggingEvent event) {
        StringBuilder result = new StringBuilder(layout.format(event));
        String s = event.getRenderedMessage();
        result.append(s).append(Layout.LINE_SEP).append("</br>");
        return result.toString();
    }

    @Override
    public void close()
    {
    }

    @Override
    public boolean requiresLayout()
    {
        return true;
    }
}
