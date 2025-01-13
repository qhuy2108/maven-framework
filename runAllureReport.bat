set ProjectPath=%~dp0
cd %ProjectPath%
echo %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%\libraries\allureReport\aspectjweaver-1.9.22.1.jar" -classpath "%ProjectPath%out\production\hybrid-nopcommerce-framwork;%ProjectPath%libraries\allureReport\*;%ProjectPath%libraries\extentReport\*;%ProjectPath%libraries\log4j-2x\*;%ProjectPath%libraries\*;" org.testng.TestNG "%ProjectPath%resources\allureNopcommerce.xml"
allure serve .\allure-results\
pause