package com.epam.tat.framework.runner;

import com.beust.jcommander.Parameter;
import com.epam.tat.framework.ui.BrowserType;
import com.epam.tat.framework.ui.BrowserTypeConverter;

import java.util.List;

public class Parameters {

    private static Parameters instance;

    @Parameter(names = { "--suites", "-s" }, description = "The list of TestNG suites", required = true)
    private List<String> suites;

    @Parameter(names = { "--browser", "-b" }, description = "Browser type", converter = BrowserTypeConverter.class)
    private BrowserType browserType;

    @Parameter(names = { "--logger", "-l" }, description = "Log4j.xml config")
    private String logger = "./log4j.xml";

    private Parameters() {}

    public static synchronized Parameters instance() {
        if (instance == null) {
            return new Parameters();
        }
        return instance;
    }

    public List<String> getSuites() {
        return suites;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public String getLogger() {
        return logger;
    }
}
