package com.facebook.cases;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.livetechpanda.HomePO;
import pageObjects.livetechpanda.PageGenerator;

public class Level_18_Undisplayed extends BaseTest {

    private WebDriver driver;
    String browserName;

    private HomePO homePO;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        homePO = PageGenerator.getLoginPage(driver);

    }


    @Test
    public void TC_01_Element_Undisplayed() {
        homePO.clickToHeaderAccountButton();

        // Case 1 : verify my account link is displayed
        Assert.assertTrue(homePO.isMyAccountLinkDisplayed());
        sleepInSecond(2);

        // out date FB xem video + tai lieu excel
    }

    @Test
    public void TC_02(){
        homePO.clickToHeaderAccountButton();
        // Case 2 : verify my account link is NOT displayed -- present
        Assert.assertFalse(homePO.isMyAccountLinkDisplayed());
        sleepInSecond(2);
    }

    @Test
    public void TC_03(){
        // Case 3 : verify is NOT displayed
        Assert.assertTrue(homePO.isNotMyAccountLinkDisplayed());
    }


    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
