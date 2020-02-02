package com.epam.tat.framework.runner;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import com.epam.tat.framework.ui.BrowserType;
import com.epam.tat.framework.util.ParallelModeValidator;
import com.epam.tat.framework.util.ThreadValidator;
import org.testng.xml.XmlSuite.ParallelMode;

import java.util.ArrayList;
import java.util.List;

public class Arguments {

    private static Arguments instance;

    @Parameter(names = {"--suites", "-s"}, description = "The list of TestNG suites", required = true)
    private List<String> suites = new ArrayList<>();

    @Parameter(names = {"--browser", "-b"}, description = "Browser type", required = true,
               converter = BrowserTypeConverter.class)
    private BrowserType browserType;

    @Parameter(names = {"--logger", "-l"}, description = "Log4j.xml config")
    private String logger = "./src/main/resources/log4j.xml";

    @Parameter(names = {"--parallel", "-p"}, description = "Entities that you want to run in parallel",
               validateWith = ParallelModeValidator.class,
               converter = ParallelModeConverter.class)
    private ParallelMode parallel = ParallelMode.NONE;

    @Parameter(names = {"--threads", "-t"}, description = "Threads count",
               validateWith = { PositiveInteger.class, ThreadValidator.class },
               converter = ThreadsConverter.class)
    private Integer threads = 1;

    public Arguments() {
    }

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

    public ParallelMode getParallel() {
        return parallel;
    }

    public Integer getThreads() {
        return threads;
    }

    public static class BrowserTypeConverter implements IStringConverter<BrowserType> {
        @Override
        public BrowserType convert(String s) {
            return BrowserType.valueOf(s.toUpperCase());
        }
    }

    public static class ParallelModeConverter implements IStringConverter<ParallelMode> {
        @Override
        public ParallelMode convert(String s) {
            return ParallelMode.valueOf(s.toUpperCase());
        }
    }

    public static class ThreadsConverter implements IStringConverter<Integer> {
        @Override
        public Integer convert(String s) {
            return Integer.valueOf(s);
        }
    }
}
