package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BaseTestBrowserConfig {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.get("https://demo.nopcommerce.com/");
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList){
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().setSize(new Dimension(1600,960));
        driver.get(url);
        return driver;
    }

    protected WebDriver getBrowserDriverWithProfile(String browserName, String url){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList){
            case FIREFOX:
                String profilePath = "C:/Users/Quanghuy/AppData/Roaming/Mozilla/Firefox/Profiles/HuyTest";  // Sửa lại đường dẫn cho đúng
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile(new File(profilePath));

                firefoxOptions.setProfile(profile);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--user-data-dir=C:/Users/QuangHuy/AppData/Local/Microsoft/Edge/User Data/");
                edgeOptions.addArguments("--profile-directory=Profile 1");
                driver = new EdgeDriver(edgeOptions);
                break;

            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--user-data-dir=C:/Users/QuangHuy/AppData/Local/Google/Chrome/User Data/");
                chromeOptions.addArguments("--profile-directory=Profile 1");
                driver = new ChromeDriver(chromeOptions);
                break;

            default:
                throw new RuntimeException("Browser name is not valid.");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().setSize(new Dimension(1600,960));
        driver.get(url);
        return driver;
    }

    protected WebDriver getBrowserDriverConfig(String browserName, String url){
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        Path path = null;
        File extensionFilePath = null;

        switch (browserList){
            case FIREFOX:
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("dom.webnotifications.enable", false);
                firefoxOptions.addPreference("geo.enable", false);
                firefoxOptions.addPreference("geo.provider.use_corelocation", false);
                firefoxOptions.addArguments("-private");

                driver = new FirefoxDriver(firefoxOptions);
                path = Paths.get(GlobalConstants.BROWSER_EXTENSION_PATH + "wappalyzer-6.10.76.xpi");
                FirefoxDriver firefoxDriver = (FirefoxDriver) driver;
                firefoxDriver.installExtension(path);
                break;

            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--user-data-dir=C:/Users/QuangHuy/AppData/Local/Google/Chrome/User Data/");
//                chromeOptions.addArguments("--profile-directory=Profile 1");
//                chromeOptions.addArguments("-headless");
                chromeOptions.addArguments("window-size=1600x900");
                path = Paths.get(GlobalConstants.BROWSER_EXTENSION_PATH + "Wappalyzer-6.10.77.0.crx");
                extensionFilePath = new File(path.toUri());
                chromeOptions.addExtensions(extensionFilePath);
                driver = new ChromeDriver(chromeOptions);
                break;

            case EDGE:
                EdgeOptions edgeOptions = new EdgeOptions();
//                chromeOptions.addArguments("--user-data-dir=C:/Users/QuangHuy/AppData/Local/Google/Chrome/User Data/");
//                chromeOptions.addArguments("--profile-directory=Profile 1");
//                chromeOptions.addArguments("-headless");
                edgeOptions.addArguments("window-size=1600x900");
                path = Paths.get(GlobalConstants.BROWSER_EXTENSION_PATH + "Wappalyzer-6.10.77.0.crx");
                extensionFilePath = new File(path.toUri());
                edgeOptions.addExtensions(extensionFilePath);
                driver = new EdgeDriver(edgeOptions);
                break;

            case HFIREFOX:
//                String profilePath = "C:/Users/Quanghuy/AppData/Roaming/Mozilla/Firefox/Profiles/HuyTest";  // Sửa lại đường dẫn cho đúng
//                FirefoxProfile profile = new FirefoxProfile(new File(profilePath));
                FirefoxOptions hfirefoxOptions = new FirefoxOptions();
                hfirefoxOptions.addArguments("-headless");
                hfirefoxOptions.addArguments("window-size=1600x900");
                driver = new FirefoxDriver(hfirefoxOptions);
                break;

            case HEDGE:
                EdgeOptions hedgeOptions = new EdgeOptions();
                hedgeOptions.addArguments("--user-data-dir=C:/Users/QuangHuy/AppData/Local/Microsoft/Edge/User Data/");
                hedgeOptions.addArguments("--profile-directory=Profile 1");
                hedgeOptions.addArguments("-headless");
                hedgeOptions.addArguments("window-size=1600x900");
                hedgeOptions.addExtensions();
                driver = new EdgeDriver(hedgeOptions);
                break;

            case HCHROME:
                ChromeOptions hchromeOptions = new ChromeOptions();
                hchromeOptions.addArguments("--user-data-dir=C:/Users/QuangHuy/AppData/Local/Google/Chrome/User Data/");
                hchromeOptions.addArguments("--profile-directory=Profile 1");
                hchromeOptions.addArguments("-headless");
                hchromeOptions.addArguments("window-size=1600x900");
                hchromeOptions.addExtensions();
                driver = new ChromeDriver(hchromeOptions);
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().setSize(new Dimension(1600,960));
        driver.get(url);
        return driver;
    }



    protected void assertTrue(boolean condition) {
        Assert.assertTrue(VerifyTrue(condition));
    }

    protected boolean VerifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;

    }

    protected boolean VerifyFalse(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean VerifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;

    }

    protected final Logger log;
    public BaseTestBrowserConfig() {
        log = LogManager.getLogger(getClass());
    }

    //@BeforeSuite
    public void deleteFileInReport() {
        // Remove all file in ReportNG screenshot (image)
        deleteAllFileInFolder("reportNGImage");

        // Remove all file in Allure attachment (json file)
        deleteAllFileInFolder("allure-results");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRandomNumber() {
        return new Random().nextInt(9999);
    }
    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
