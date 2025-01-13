package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.users.UserCustomerInfoPO;
import pageObjects.nopCommerce.users.UserHomePO;
import pageObjects.nopCommerce.users.UserLoginPO;
import pageObjects.nopCommerce.users.UserRegisterPO;

import java.time.Duration;

public class Level_03_Page_Object_Pattern extends BaseTest {
    private WebDriver driver;

    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPO loginPage;
    private UserCustomerInfoPO customerInfoPage;

    String emailAddress, firstName, lastName, password, companyName, day, month, year;

    @BeforeClass
    public void beforeClass() {

        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--user-data-dir=C:/Users/QuangHuy/AppData/Local/Microsoft/Edge/User Data/");
        edgeOptions.addArguments("--profile-directory=Profile 1");
        driver = new EdgeDriver(edgeOptions);


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.get("https://demo.nopcommerce.com/");

        homePage = new UserHomePO(driver);

        emailAddress = "Kaka" + getRandomNumber() + "@gmail.net";
        firstName = "Ricardo";
        lastName = "Kaka";
        day = "18";
        month = "September";
        year = "2022";
        password = "123456";
        companyName = "Milan";
    }


    @Test
    public void User_01_Register() {
        homePage.clickToRegisterLink();

        registerPage = new UserRegisterPO(driver);

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

        Assert.assertEquals(registerPage.getRegisteredSuccessMessage() , "Your registration completed");

    }

    @Test
    public void User_02_Login() {
        registerPage.clickToLogoutButton();

        homePage = new UserHomePO(driver);
        homePage.clickToLoginLink();

        loginPage = new UserLoginPO(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());


    }

    @Test
    public void User_03_MyAccount() {
        homePage.clickToMyAccountLink();

        customerInfoPage = new UserCustomerInfoPO(driver);

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
