package com.epam.tat.framework.runner;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import com.epam.tat.framework.ui.BrowserType;
import com.epam.tat.framework.util.FileValidator;

import java.util.ArrayList;
import java.util.List;

public class Arguments {

    private static Arguments instance;

    @Parameter(names = { "--suites", "-s" }, description = "The list of TestNG suites",
             validateWith = FileValidator.class)
    private List<String> suites = new ArrayList<>();

    @Parameter(names = { "--browser", "-b" }, description = "Browser type", converter = BrowserTypeConverter.class,
    required = true)
    private BrowserType browserType;

    @Parameter(names = { "--logger", "-l" }, description = "Log4j.xml config", validateWith = FileValidator.class)
    private String logger = "./src/main/resources/log4j.xml";

    public Arguments() {}

    public static synchronized Arguments instance() {
        if (instance == null) {
            instance = new Arguments();
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

    public static class BrowserTypeConverter implements IStringConverter<BrowserType> {
        @Override
        public BrowserType convert(String s) {
            return BrowserType.valueOf(s.toUpperCase());
        }
    }
}
