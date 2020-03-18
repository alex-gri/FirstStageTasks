To avoid error in test where folder with the same name already exists change Examples at files.feature file.

Package project using maven and run it from 'demo-jar-with-dependencies' using console:
java -jar demo-jar-with-dependencies.jar --suites testng.xml

To connect to specific hub:
java -jar demo-jar-with-dependencies.jar --suites testng-all.xml --host localhost --port 4444


Arguments in short:
    {"--suites", "-s"}, description = "The list of TestNG suites", required = true
    
    {"--browser", "-b"}, description = "Browser type", default - CHROME
    
    {"--logger", "-l"}, description = "Log4j.xml config", default - log4j.xml
    
    {"--parallel", "-par"}, description = "Entities that you want to run in parallel", default - NONE
    
    {"--threads", "-t"}, description = "Threads count", default = 1

    {"--host", "-h"}, description = "Selenium grid host", default - localhost

    {"--port", "-p"}, description = "Selenium port", default - 4444;
