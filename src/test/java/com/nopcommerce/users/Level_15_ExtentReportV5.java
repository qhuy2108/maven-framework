package com.nopcommerce.users;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.users.*;
import reportConfig.ExtentManager;

import java.lang.reflect.Method;

public class Level_15_ExtentReportV5 extends BaseTest {
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;

    //topic 74
    private UserRewardPointPO rewardPointPage;
    private UserOrderPO orderPage;
    private UserAddressPO addressPage;
    // -------------------------------------------------------------------------------------

    String browserName;

    String emailAddress, firstName, lastName, password, companyName, day, month, year;

    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        emailAddress = "Kaka" + getRandomNumber() + "@gmail.net";
        firstName = "Ricardo";
        lastName = "Kaka";
        day = "18";
        month = "September";
        year = "2022";
        password = "123456";
        companyName = "Milan";

        homePage = PageGenerator.getUserHomePage(driver);

        this.browserName = browserName.toUpperCase();
    }


    @Test
    public void User_01_Register(Method method) {
        ExtentManager.startTest(method.getName() + " - " + browserName, "User_01_Register");
        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 01: Open Register page");
        registerPage =  homePage.clickToRegisterLink();

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 02: Click to Male radio button");
        registerPage.clickToMaleRadio();

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 03: Enter to FirstName textbox with value " + firstName);
        registerPage.enterToFirstName(firstName);

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 04: Enter to LastName textbox with value " + lastName);
        registerPage.enterToLastName(lastName);

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 05: Select Day dropdown with value " + day);
        registerPage.selectDayDropdown(day);

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 06: Select Month dropdown with value " + month);
        registerPage.selectMonthDropdown(month);

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 07: Select Year dropdown with value " + year);
        registerPage.selectYearDropdown(year);

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 08: Select Email Address textbox with value " + emailAddress);
        registerPage.enterToEmailTextbox(emailAddress);

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 09: Select Company textbox with value " + companyName);
        registerPage.enterToCompanyTextbox(companyName);

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 10: Select Password textbox with value " + password);
        registerPage.enterToPasswordTextbox(password);

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 11: Select Confirm Password textbox with value " + password);
        registerPage.enterToConfirmPasswordTextbox(password);


        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 12: Click to Register button");
        registerPage.clickToRegisterButton();

        ExtentManager.getTest().log(Status.INFO, "User_01_Register - STEP 13: Verify success message is displayed");
        Assert.assertEquals(registerPage.getRegisteredSuccessMessage() , "Your registration completed ...Fail...! ");

    }

    @Test
    public void User_02_Login(Method method) {
        ExtentManager.startTest(method.getName() + " - " + browserName, "User_02_Login");
        registerPage.clickToLogoutButton(); // quay ra homePage

        // đang ở trang homePage gọi ra loginPage
        loginPage = homePage.clickToLoginLink();

        loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @Test
    public void User_03_MyAccount(Method method) {
        ExtentManager.startTest(method.getName() + " - " + browserName, "User_03_MyAccount");

        customerInfoPage = homePage.clickToMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);

        Assert.assertEquals(customerInfoPage.getDayDropdownSelectedValue(), day);
        Assert.assertEquals(customerInfoPage.getMonthDropdownSelectedValue(), month);
        Assert.assertEquals(customerInfoPage.getYearDropdownSelectedValue(),year);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(),companyName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(),emailAddress);
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
