package com.nopcommerce.users;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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


@Feature("User")
public class Level_16_AllureReport extends BaseTest {
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

    @Description("Register to application")
    @Severity(SeverityLevel.NORMAL)

    @Test
    public void User_01_Register(Method method) {
        registerPage = homePage.clickToRegisterLink();

        registerPage.clickToMaleRadio();

        registerPage.enterToFirstName(firstName);

        registerPage.enterToLastName(lastName);

        registerPage.selectDayDropdown(day);

        registerPage.selectMonthDropdown(month);

        registerPage.selectYearDropdown(year);

        registerPage.enterToEmailTextbox(emailAddress);

        registerPage.enterToCompanyTextbox(companyName);

        registerPage.enterToPasswordTextbox(password);

        registerPage.enterToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisteredSuccessMessage(), "Your registration completed ...Fail...! ");

    }

    @Description("Login to application")
    @Severity(SeverityLevel.MINOR)

    @Test
    public void User_02_Login(Method method) {

        registerPage.clickToLogoutButton(); // quay ra homePage

        // đang ở trang homePage gọi ra loginPage
        loginPage = homePage.clickToLoginLink();

        loginPage.loginToSystem(emailAddress, password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }


    @Description("Verify Myaccount Info")
    @Severity(SeverityLevel.CRITICAL)

    @Test
    public void User_03_MyAccount(Method method) {

        customerInfoPage = homePage.clickToMyAccountLink();

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);

        Assert.assertEquals(customerInfoPage.getDayDropdownSelectedValue(), day);
        Assert.assertEquals(customerInfoPage.getMonthDropdownSelectedValue(), month);
        Assert.assertEquals(customerInfoPage.getYearDropdownSelectedValue(), year);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(), companyName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }
}
