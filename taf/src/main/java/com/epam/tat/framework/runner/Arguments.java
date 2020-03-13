package com.epam.tat.framework.runner;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import com.epam.tat.framework.runner.cli.*;
import org.testng.xml.XmlSuite.ParallelMode;

import java.util.ArrayList;
import java.util.List;

public class Arguments {

    private static Arguments instance;

    @Parameter(names = {"--suites", "-s"}, description = "The list of TestNG suites", required = true)
    private List<String> suites = new ArrayList<>();

    @Parameter(names = {"--browser", "-b"}, description = "Browser type",
               converter = BrowserTypeConverter.class)
    private BrowserType browserType = BrowserType.CHROME;

    @Parameter(names = {"--logger", "-l"}, description = "Log4j.xml config")
    private String logger = "./src/main/resources/log4j.xml";

    @Parameter(names = {"--parallel", "-p"}, description = "Entities that you want to run in parallel",
               validateWith = ParallelModeValidator.class,
               converter = ParallelModeConverter.class)
    private ParallelMode parallel = ParallelMode.NONE;

    @Parameter(names = {"--threads", "-t"}, description = "Threads count",
               validateWith = { PositiveInteger.class, ThreadValidator.class },
               converter = ThreadCountConverter.class)
    private Integer threadCount = 1;

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

    public Integer getThreadCount() {
        return threadCount;
    }
}
