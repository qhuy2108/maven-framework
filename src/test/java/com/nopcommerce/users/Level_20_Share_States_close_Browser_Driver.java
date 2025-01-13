package com.nopcommerce.users;

import com.nopcommerce.commons.Level_20_Share_States_Login;
import commons.BaseTest;
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

public class Level_20_Share_States_close_Browser_Driver extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;

    String emailAddress, firstName, lastName, password, companyName, day, month, year;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGenerator.getUserHomePage(driver);

        //emailAddress = "Kaka" + getRandomNumber() + "@gmail.net";
        firstName = "Ricardo";
        lastName = "Kaka";
        day = "18";
        month = "September";
        year = "2022";
        password = "123456";
        companyName = "Milan";

        // Pre-condition : login by cookie
        homePage.setCookies(driver, Level_20_Share_States_Login.nopCommerceCookies);
        homePage.sleepInSecond(3);
        homePage.refreshCurrentPage(driver);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_01_MyAccount() {
        customerInfoPage = homePage.clickToMyAccountLink();
        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        sleepInSecond(3);
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "FirstName"), firstName);
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "LastName"), lastName);
        Assert.assertEquals(customerInfoPage.getDayDropdownSelectedValue(), day);
        Assert.assertEquals(customerInfoPage.getMonthDropdownSelectedValue(), month);
        Assert.assertEquals(customerInfoPage.getYearDropdownSelectedValue(),year);
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "Company"),companyName);
        Assert.assertEquals(customerInfoPage.getTextboxValueByID(driver, "Email"),Level_20_Share_States_Login.emailAddress);
    }

    @Test
    public void User_02_Payment() {
    }

    @Test
    public void User_03_Order() {
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }
}
