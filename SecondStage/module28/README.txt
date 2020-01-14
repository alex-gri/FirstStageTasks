To run from cmd:
mvn -Dbrowser=chrome -Denvironment=qa -Dsurefire.suiteXmlFiles=src\test\resources\testng-cost.xml clean test


browser can be:
chrome
firefox

environment can be:
qa
dev

surefire.suiteXmlFiles can be:
src\test\resources\testng-cost.xml
src\test\resources\testng-all.xml
src\test\resources\testng-smoke.xml