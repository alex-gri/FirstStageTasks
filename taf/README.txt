To run packaged project from 'demo-jar-with-dependencies' using console:
java -jar demo-jar-with-dependencies.jar --suites testng-all.xml

To run in parallel:
java -jar demo-jar-with-dependencies.jar --suites testng-all.xml -p classes -t 2


Arguments in short:
    {"--suites", "-s"}, description = "The list of TestNG suites", required = true
    
    {"--browser", "-b"}, description = "Browser type", default - CHROME
    
    {"--logger", "-l"}, description = "Log4j.xml config", default - log4j.xml
    
    {"--parallel", "-p"}, description = "Entities that you want to run in parallel", default - NONE
    
    {"--threads", "-t"}, description = "Threads count", default = 1



