package com.nopcommerce.users;

import commons.BaseTest;
import jiraConfig.JiraCreateIssue;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.users.*;

public class Level_17_L4J_Jira extends BaseTest {
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

        emailAddress = "Kaka" + getRandomNumber() + "@gmail.net";
        firstName = "Ricardo";
        lastName = "Kaka";
        day = "18";
        month = "September";
        year = "2022";
        password = "123456";
        companyName = "Milan";

    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_01_Register() {
        log.info("User_01_Register - STEP 01: Open Register page");
        registerPage =  homePage.clickToRegisterLink();

        log.info("User_01_Register - STEP 02: Click to Male radio button");
        registerPage.clickToMaleRadio();

        log.info("User_01_Register - STEP 03: Enter to FirstName textbox with value " + firstName);
        registerPage.enterToFirstName(firstName);

        log.info("User_01_Register - STEP 04: Enter to LastName textbox with value " + lastName);
        registerPage.enterToLastName(lastName);

        log.info("User_01_Register - STEP 05: Select Day dropdown with value " + day);
        registerPage.selectDayDropdown(day);

        log.info("User_01_Register - STEP 06: Select Month dropdown with value " + month);
        registerPage.selectMonthDropdown(month);

        log.info("User_01_Register - STEP 07: Select Year dropdown with value " + year);
        registerPage.selectYearDropdown(year);

        log.info("User_01_Register - STEP 08: Select Email Address textbox with value " + emailAddress);
        registerPage.enterToEmailTextbox(emailAddress);

        log.info("User_01_Register - STEP 09: Select Company textbox with value " + companyName);
        registerPage.enterToCompanyTextbox(companyName);

        log.info("User_01_Register - STEP 10: Select Password textbox with value " + password);
        registerPage.enterToPasswordTextbox(password);

        log.info("User_01_Register - STEP 11: Select Confirm Password textbox with value " + password);
        registerPage.enterToConfirmPasswordTextbox(password);


        log.info("User_01_Register - STEP 12: Click to Register button");
        registerPage.clickToRegisterButton();

        log.info("User_01_Register - STEP 13: Verify success message is displayed");
        Assert.assertEquals(registerPage.getRegisteredSuccessMessage() , "Your registration completed FAIL");

    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_02_Login() {
        registerPage.clickToLogoutButton(); // quay ra homePage

        // đang ở trang homePage gọi ra loginPage
        loginPage = homePage.clickToLoginLink();

        loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    @JiraCreateIssue(isCreateIssue = true)
    @Test
    public void User_03_MyAccount() {
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
