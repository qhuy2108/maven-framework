package com.nopcommerce.users;

import com.nopcommerce.commons.Level_20_Share_States_Login;
import commons.BaseTest;
import commons.BaseTestBrowserConfig;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.users.UserCustomerInfoPO;
import pageObjects.nopCommerce.users.UserHomePO;
import pageObjects.nopCommerce.users.UserLoginPO;
import pageObjects.nopCommerce.users.UserRegisterPO;

public class Level_21_Browser_Config extends BaseTestBrowserConfig {
    private WebDriver driver;

    @Parameters({"browser","url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriverConfig(browserName, url) ;
    }

    @Test
    public void User_01_MyAccount() {
    }

    @Test
    public void User_02_Payment() {
    }

    @Test
    public void User_03_Order() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        //closeBrowserDriver();
    }
}
