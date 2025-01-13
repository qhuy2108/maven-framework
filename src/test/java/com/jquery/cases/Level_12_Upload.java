package com.jquery.cases;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePO;
import pageObjects.jquery.PageGenerator;

public class Level_12_Upload extends BaseTest {
    private WebDriver driver;
    private HomePO homePO;

    private String hanoiFileName = "hanoi.jpg";
    private String hueFileName = "hue.jpg";
    private String saigonFileName = "saigon.jpg";

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);
        homePO = PageGenerator.getHomePage(driver);
    }


    @Test
    public void Upload_01() {
        homePO.uploadMultipleFiles(driver, hanoiFileName);
        sleepInSecond(3);
        homePO.refreshCurrentPage(driver);

        homePO.uploadMultipleFiles(driver, hanoiFileName, hueFileName, saigonFileName);
        sleepInSecond(3);

        Assert.assertTrue(homePO.isFileLoadedByName(hanoiFileName));
        Assert.assertTrue(homePO.isFileLoadedByName(hueFileName));
        Assert.assertTrue(homePO.isFileLoadedByName(saigonFileName));

        homePO.clickToUploadButton();

        Assert.assertTrue(homePO.isFileUpLoaded(hanoiFileName));
        Assert.assertTrue(homePO.isFileUpLoaded(hueFileName));
        Assert.assertTrue(homePO.isFileUpLoaded(saigonFileName));


    }

    //@Test
    public void Table_02_Search() {

    }

    //@Test
    public void Table_03_Delete() {

    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
