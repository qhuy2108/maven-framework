package com.nopcommerce.commons;

import commons.BaseTest;
import org.apache.http.cookie.SetCookie;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.users.UserCustomerInfoPO;
import pageObjects.nopCommerce.users.UserHomePO;
import pageObjects.nopCommerce.users.UserLoginPO;
import pageObjects.nopCommerce.users.UserRegisterPO;

import java.util.Set;

public class Level_20_Share_States_Login extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;

    public String firstName, lastName, password, companyName, day, month, year;
    public static String emailAddress;

    @Parameters("browser")
    @BeforeTest
    public void beforeTest(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGenerator.getUserHomePage(driver);

        // Data Test
        emailAddress = "Kaka" + getRandomNumber() + "@gmail.net";
        firstName = "Ricardo";
        lastName = "Kaka";
        day = "18";
        month = "September";
        year = "2022";
        password = "123456";
        companyName = "Milan";

        // New User
        log.info("User_01_Register - STEP 01: Open Register page");
        registerPage =  homePage.clickToRegisterLink();

        log.info("User_01_Register - STEP 02: Click to Male radio button");
        registerPage.clickToRadioByID(driver, "gender-male");

        log.info("User_01_Register - STEP 03: Enter to FirstName textbox with value " + firstName);
        registerPage.enterToTextboxByID(driver, "FirstName", firstName);

        log.info("User_01_Register - STEP 04: Enter to LastName textbox with value " + lastName);
        registerPage.enterToTextboxByID(driver, "LastName", lastName);

        log.info("User_01_Register - STEP 05: Select Day dropdown with value " + day);
        registerPage.selectDayDropdown(day);

        log.info("User_01_Register - STEP 06: Select Month dropdown with value " + month);
        registerPage.selectMonthDropdown(month);

        log.info("User_01_Register - STEP 07: Select Year dropdown with value " + year);
        registerPage.selectYearDropdown(year);

        log.info("User_01_Register - STEP 08: Select Email Address textbox with value " + emailAddress);
        registerPage.enterToTextboxByID(driver, "Email", emailAddress);

        log.info("User_01_Register - STEP 09: Select Company textbox with value " + companyName);
        registerPage.enterToTextboxByID(driver, "Company", companyName);

        log.info("User_01_Register - STEP 10: Select Password textbox with value " + password);
        registerPage.enterToTextboxByID(driver, "Password", password);

        log.info("User_01_Register - STEP 11: Select Confirm   textbox with value " + password);
        registerPage.enterToTextboxByID(driver, "ConfirmPassword", password);


        log.info("User_01_Register - STEP 12: Click to Register button");
        registerPage.clickToButtonByText(driver, "Register");

        log.info("User_01_Register - STEP 13: Verify success message is displayed");
        Assert.assertEquals(registerPage.getRegisteredSuccessMessage() , "Your registration completed");

        registerPage.clickToLogoutButton(); // quay ra homePage

        // Login

        loginPage = homePage.clickToLoginLink();

        loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

        // Get Cookie
        nopCommerceCookies = homePage.getAllCookies(driver);

        driver.quit();

    }

    @AfterTest
    public void afterTest() {
    }

    public static Set<Cookie> nopCommerceCookies;

}
