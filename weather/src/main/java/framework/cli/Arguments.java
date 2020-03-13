package framework.cli;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Arguments {
    private static Arguments instance;

    @Parameter(names = {"--suites", "-s"}, description = "The list of TestNG suites", required = true)
    private List<String> suites = new ArrayList<>();

    @Parameter(names = {"--logger", "-l"}, description = "Log4j.xml config")
    private String logger = "./src/main/resources/log4j.xml";

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

    public String getLogger() {
        return logger;
    }
}
